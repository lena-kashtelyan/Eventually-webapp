package edu.brown.cs.finalproject.credentials;

public class SignUp {
  private String firstName, lastName, username, email, password;

  public SignUp(String firstName, String lastName, String username,
      String email, String password) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.username = username;
    this.email = email;
    this.password = password;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getUsername() {
    return username;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  // public static boolean isUnique(String username) {
  // Connection conn = Database.getConnection();
  // System.out.println(conn);
  // String query = "SELECT COUNT(username) FROM users WHERE
  // username=?";
  // System.out.println("Is unique username is: " +
  // username);
  // try (PreparedStatement prep =
  // conn.prepareStatement(query)) {
  // prep.setString(1, username);
  // try (ResultSet rs = prep.executeQuery()) {
  // int count = rs.getInt(1);
  // if (count == 0) {
  // return true;
  // } else {
  // return false;
  // }
  // }
  // } catch (SQLException n) {
  // n.printStackTrace();
  // return false;
  // }
  // }
  //
  // /**
  // * @param fields
  // * - array of fields for new user ordered by the SQL
  // table
  // * @param conn
  // * - connection to database
  // * @return true if user was added, false if not
  // */
  // public static boolean addUser(String[] fields) {
  // if (fields.length != 8) {
  // System.out.println("fields must contain exactly 8
  // values");
  // return false;
  // }
  //
  // Connection conn = Database.getConnection();
  //
  // if (isUnique(fields[0])) {
  // String query = "INSERT INTO users VALUES
  // (?,?,?,?,?,?,?,?,?)";
  // try (PreparedStatement prep =
  // conn.prepareStatement(query)) {
  // prep.setString(1, UUID.randomUUID().toString());
  // for (int i = 0; i < fields.length; i++) {
  // prep.setString(i + 2, fields[i]);
  // }
  // prep.addBatch();
  // prep.executeBatch();
  // return true;
  // } catch (NullPointerException | SQLException n) {
  // n.printStackTrace();
  // return false;
  // }
  // } else {
  // return false;
  // }
  // }

}
