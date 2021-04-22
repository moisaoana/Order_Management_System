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
/**
 *  This class defines the common operations for accessing a table from a database.
 *  @author Moisa Oana Miruna
 *  @version 1.0
 *  @since 22.04.2021
 * @param <T> the type of the object from the table that is accessed
 */
public class AbstractDAO <T>{
    protected static final Logger LOGGER=Logger.getLogger(AbstractDAO.class.getName());
    private final Class<T> type;
    /**
     * For each AbstractDAO object obtain the class of the generic type T
     */
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
        stringBuilder.append(" WHERE ");
        stringBuilder.append(field);
        stringBuilder.append(" =?");
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
        stringBuilder.append(" WHERE ");
        stringBuilder.append(field);
        stringBuilder.append("=?");
        return stringBuilder.toString();
    }
    private String createUpdateQuery(String field){
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
        stringBuilder.append("WHERE ");
        stringBuilder.append(field);
        stringBuilder.append(" = ? ");
        return stringBuilder.toString();
    }

    /**
     * Method for finding an object from the table based on its id
     * @param id an integer representing the id of the object
     * @return the object of generic type T
     */
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

    /**
     * Method for inserting an object in a table from the database
     * @param element an object of generic type T
     * @return the id of the inserted element
     */
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

    /**
     * Method for deleting an element from a table from the database
     * @param id an int representing the id of the element to be deleted
     * @param field a String representing the field based on which the element is deleted
     */
    public void deleteElement(int id,String field){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String query=createDeleteQuery(field);
        try{
            connection= ConnectionFactory.getConnection();
            preparedStatement=connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
           preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            LOGGER.log(Level.WARNING,type.getName()+"delete",throwables.getMessage());
            System.out.println(throwables.getMessage());
        }finally{
            ConnectionFactory.close(preparedStatement);
            ConnectionFactory.close(connection);
        }
    }

    /**
     * Method for updating the fields of an already existing object from the table in the database
     * @param element an object of generic type T that we want to update
     */
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

    /**
     * Method that receives a result set and returns a list of objects
     * @param resultSet an object of type ResultSet
     * @return a list of objects of generic type T
     */
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

    /**
     * Method that finds an element in the table based on its name
     * @param name a String representing the name field of the object
     * @return an object of generic type T
     */
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

    /**
     * Method that returns all entries of a table from the database
     * @return a list of object of generic type T
     */
    public List<T> findAll() {
        List<T> list=new ArrayList<T>();
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        String query=createSelectAllQuery();
        System.out.println(query);
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

    /**
     * Method that receives a list of objects of type T and generates the header of the corresponding table and populates it
     * @param tableView an object of type TableView that represents the resulting table
     * @param list a list of objects of generic type T that we want to introduce in the table
     * @param observableList the observable list of the tableView
     */
    public void displayTable(TableView<T> tableView, List<T>list, ObservableList<T> observableList ){
        for( int i=0;i<type.getDeclaredFields().length;i++) {
            Field field=type.getDeclaredFields()[i];
            TableColumn<T,Object> column = new TableColumn<T,Object>(field.getName());
            column.setCellValueFactory(new PropertyValueFactory<>(field.getName()));
            column.setStyle("-fx-background-color: aliceblue;");
            tableView.getColumns().add(column);
        }
        observableList.addAll(list);
        tableView.setItems(observableList);
    }
}
