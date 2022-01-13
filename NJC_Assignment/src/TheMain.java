import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TheMain {
    public static void main(String args[]){
        insert("Suicide Squad","Jared Leto","Margot Robbie"," 5 August 2016"," David Ayer");
        insert("Blade Runner 2049","Ryan Gosling","Ana de Armas","6 October 2017 ","Denis Villeneuve");
        insert("Dune","Denis Villeneuve","Zendaya","22 October 2021 (India)","Denis Villeneuve");
        insert("No Time to Die","Daniel Craig","Léa Seydoux","30 September 2021","Cary Joji Fukunaga");
        insert("Spectre","Daniel Craig","Léa Seydoux"," 20 November 2015","Sam Mendes");
        insert("Skyfall","Daniel Craig","Naomie Harris","1 November 2012 ","Sam Mendes");
        insert("World War Z","Brad Pitt","Brad Pitt","21 June 2013","Marc Forster");
        insert("Gone Girl","Ben Affleck","Rosamund Pike","10 October 2014"," David Fincher");
        insert("Seven","Brad Pitt","Gwyneth Paltrow"," 22 September 1995"," David Fincher");
        insert("Fight Club", "Brad Pitt", "Helena Bonham Carter", "11 November 1999","David Fincher");
        insert("War Dogs","Miles Teller","Ana de Armas","19 August 2016","Todd Phillips");
        insert("War Dogs","Miles Teller","Ana de Armas","19 August 2016","Todd Phillips");
        readAllData();
    }


    private static void insert(String movieName, String actor, String actress, String releaseDate, String director) {
        Connection con = DbConnection.connect();
        PreparedStatement ps = null; 
        try {
          String sql = "INSERT INTO movies(movieName, actor, actress, releaseDate, director) VALUES(?,?,?,?,?) ";
          ps = con.prepareStatement(sql);
          ps.setString(1, movieName);
          ps.setString(2, actor);
          ps.setString(3, actress);
          ps.setString(4, releaseDate);
          ps.setString(5, director);
          ps.execute();
          System.out.println("Data has been inserted!");
        } catch(SQLException e) {
          System.out.println(e.toString());
          // always remember to close database connections
        } finally {
          try{
            ps.close();
            con.close();
          } catch(SQLException e) {
            System.out.println(e.toString());
          }
          
        }
    }

    private static void readAllData() {
        Connection con = DbConnection.connect(); 
        PreparedStatement ps = null; 
        ResultSet rs = null; 
        
        try {
          String sql = "SELECT * FROM movies";
          ps = con.prepareStatement(sql); 
          rs = ps.executeQuery();
          System.out.println("ALL USERS\n");
          while(rs.next()) {
            String movieName = rs.getString("movieName"); 
            String actor = rs.getString("actor"); 
            String actress = rs.getString("actress"); 
            String releaseDate = rs.getString("releaseDate"); 
            String director = rs.getString("director"); 
            
            
            
            System.out.println("movie Name: "+movieName);
            System.out.println("actor Name: "+actor);
            System.out.println("actress: "+actress);
            System.out.println("director: "+director);
            System.out.println("releaseDate: "+releaseDate+"\n\n");
            
          }
        } catch(SQLException e) {
          System.out.println(e.toString());
        } finally {
          try {
            rs.close();
            ps.close();
            con.close(); 
          } catch(SQLException e) {
            System.out.println(e.toString());
          }
        }
        
        
      }
}
