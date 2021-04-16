package sample.dataAccessLayer;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.connection.ConnectionFactory;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AbstractDAO <T>{
    protected static final Logger LOGGER=Logger.getLogger(AbstractDAO.class.getName());
    private final Class<T> type;
    @SuppressWarnings("unchecked")
    public AbstractDAO(){
        this.type=(Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
    private String createSelectQuery(String field){
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("SELECT ");
        stringBuilder.append("* ");
        stringBuilder.append("FROM ");
        stringBuilder.append(type.getSimpleName());
        stringBuilder.append(" WHERE "+ field+" =?");
        return stringBuilder.toString();
    }
    private String createSelectAllQuery(){
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("SELECT ");
        stringBuilder.append("* ");
        stringBuilder.append("FROM ");
        stringBuilder.append(type.getSimpleName());
        return  stringBuilder.toString();
    }
    private String createInsertQuery(){
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("INSERT INTO ");
        stringBuilder.append(type.getSimpleName());
        stringBuilder.append("(");
        for(int i=1;i<type.getDeclaredFields().length;i++) {
            stringBuilder.append(type.getDeclaredFields()[i].getName());
            if(i!=type.getDeclaredFields().length-1){
                stringBuilder.append(",");
            }
        }
        stringBuilder.append(") VALUES(");
        for(int i=1;i<type.getDeclaredFields().length;i++) {
            stringBuilder.append("?");
            if(i!=type.getDeclaredFields().length-1){
                stringBuilder.append(",");
            }
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
    private String createDeleteQuery(String field){
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("DELETE FROM ");
        stringBuilder.append(type.getSimpleName());
        stringBuilder.append(" WHERE "+ field+"=?");
        return stringBuilder.toString();
    }
    public String createUpdateQuery(String field){
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("UPDATE ");
        stringBuilder.append(type.getSimpleName());
        stringBuilder.append(" SET ");
        for(int i=1;i<type.getDeclaredFields().length;i++) {
            stringBuilder.append(type.getDeclaredFields()[i].getName()+" = ");
            if(i!=type.getDeclaredFields().length-1){
                stringBuilder.append("?, ");
            }else{
                stringBuilder.append("? ");
            }
        }
        stringBuilder.append("WHERE "+field+" = ? ");
        return stringBuilder.toString();
    }
    public  T findById(int id){
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        String query=createSelectQuery("id");
        try{
            connection= ConnectionFactory.getConnection();
            preparedStatement=connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            resultSet=preparedStatement.executeQuery();
            return createObjects(resultSet).get(0);
        } catch (SQLException throwables) {
          LOGGER.log(Level.WARNING,type.getName()+"find by id",throwables.getMessage());
        }catch(IndexOutOfBoundsException indexOutOfBoundsException){
            return null;
        }
        finally{
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(preparedStatement);
            ConnectionFactory.close(connection);
        }
        return null;
    }
    public int insertElement(T element){
        Connection connection = null;
        PreparedStatement insertStatement = null;
        String query=createInsertQuery();
        ResultSet rs=null;
        int insertedId = -1;
        try {
            connection=ConnectionFactory.getConnection();
            insertStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            for(int i=1;i<type.getDeclaredFields().length;i++) {
                Field field=type.getDeclaredFields()[i];
                PropertyDescriptor propertyDescriptor=new PropertyDescriptor(field.getName(),type);
                Method method=propertyDescriptor.getReadMethod();
               insertStatement.setObject(i,method.invoke(element));
            }
            insertStatement.executeUpdate();
            rs = insertStatement.getGeneratedKeys();
            if (rs.next()) {
                insertedId = rs.getInt(1);
            }
        } catch (SQLException | IntrospectionException e) {
            LOGGER.log(Level.WARNING, type.getName()+"insert",e.getMessage());
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(connection);
        }
        return insertedId;
    }
    public void deleteElement(int id){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String query=createDeleteQuery("id");
        try{
            connection= ConnectionFactory.getConnection();
            preparedStatement=connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
           preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            LOGGER.log(Level.WARNING,type.getName()+"delete",throwables.getMessage());
        }finally{
            ConnectionFactory.close(preparedStatement);
            ConnectionFactory.close(connection);
        }
    }
    public void updateElement(T element){
        Connection connection = null;
        PreparedStatement updateStatement = null;
        String query=createUpdateQuery("id");
        try {
            connection=ConnectionFactory.getConnection();
            updateStatement = connection.prepareStatement(query);
            int i;
            for( i=1;i<type.getDeclaredFields().length;i++) {
                Field field=type.getDeclaredFields()[i];
                PropertyDescriptor propertyDescriptor=new PropertyDescriptor(field.getName(),type);
                Method method=propertyDescriptor.getReadMethod();
                updateStatement.setObject(i,method.invoke(element));
            }
            Field field=type.getDeclaredFields()[0];
            PropertyDescriptor propertyDescriptor=new PropertyDescriptor(field.getName(),type);
            Method method=propertyDescriptor.getReadMethod();
            updateStatement.setObject(i,method.invoke(element));
            updateStatement.executeUpdate();
        } catch (InvocationTargetException | IntrospectionException | SQLException | IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(updateStatement);
            ConnectionFactory.close(connection);
        }
    }
    private List<T> createObjects(ResultSet resultSet){
        List<T> listOfObjects=new ArrayList<T>();
        try{
            while(resultSet.next()){
                T object=type.newInstance();
                for(Field field:type.getDeclaredFields()){
                    Object value=resultSet.getObject(field.getName());
                    PropertyDescriptor propertyDescriptor=new PropertyDescriptor(field.getName(),type);
                    Method method=propertyDescriptor.getWriteMethod();
                    method.invoke(object,value);
                }
                listOfObjects.add(object);
            }
        }catch(InstantiationException | SQLException | IllegalAccessException | IntrospectionException | InvocationTargetException e){
            e.printStackTrace();
        }
        return listOfObjects;

    }
    public  T findByName(String name){
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        String query=createSelectQuery("name");
        try{
            connection= ConnectionFactory.getConnection();
            preparedStatement=connection.prepareStatement(query);
            preparedStatement.setString(1,name);
            resultSet=preparedStatement.executeQuery();
            return createObjects(resultSet).get(0);
        } catch (SQLException throwables) {
            LOGGER.log(Level.WARNING,type.getName()+"find by name",throwables.getMessage());
        }catch(IndexOutOfBoundsException indexOutOfBoundsException){
            return null;
        }
        finally{
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(preparedStatement);
            ConnectionFactory.close(connection);
        }
        return null;
    }
    public List<T> findAll() {
        List<T> list=new ArrayList<T>();
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        String query=createSelectAllQuery();
        try{
            connection= ConnectionFactory.getConnection();
            preparedStatement=connection.prepareStatement(query);
            resultSet=preparedStatement.executeQuery();
            return createObjects(resultSet);
        } catch (SQLException throwables) {
            LOGGER.log(Level.WARNING,type.getName()+"find all",throwables.getMessage());
        }catch(IndexOutOfBoundsException indexOutOfBoundsException){
            return null;
        }
        finally{
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(preparedStatement);
            ConnectionFactory.close(connection);
        }
        return null;

    }
    /*
    public void displayTable(TableView<T> tableView, List<T>list){
        for( int i=0;i<type.getDeclaredFields().length;i++) {
            Field field=type.getDeclaredFields()[i];
            TableColumn column = new TableColumn(field.getName());
            column.setCellValueFactory(new PropertyValueFactory<>(field.getName()));
            column.setStyle("-fx-background-color: aliceblue;");
            tableView.getColumns().add(column);
        }
        for (T t : list) {
            tableView.getItems().add(t);
        }
    }

     */
    public void displayTable(TableView<T> tableView, List<T>list, ObservableList<T> observableList ){
        for( int i=0;i<type.getDeclaredFields().length;i++) {
            Field field=type.getDeclaredFields()[i];
            TableColumn column = new TableColumn(field.getName());
            column.setCellValueFactory(new PropertyValueFactory<>(field.getName()));
            column.setStyle("-fx-background-color: aliceblue;");
            tableView.getColumns().add(column);
        }
        for (T t : list) {
            observableList.add(t);
        }
        tableView.setItems(observableList);
    }
}
