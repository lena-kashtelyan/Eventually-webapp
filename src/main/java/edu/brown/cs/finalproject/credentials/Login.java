package edu.brown.cs.finalproject.credentials;

public class Login {

  private String usernameOrEmail;
  private String rawPassword;

  public Login(String userId, String password) {
    usernameOrEmail = userId;
    rawPassword = password;
  }

  public String getUsernameOrEmail() {
    return usernameOrEmail;
  }

  public String getRawPassword() {
    return rawPassword;
  }

  // public static boolean validLogin(String username,
  // String password) {
  // Connection conn = Database.getConnection();
  // String query = "SELECT COUNT(username) FROM users WHERE
  // username=? AND password=?";
  // try (PreparedStatement prep =
  // conn.prepareStatement(query)) {
  // prep.setString(1, username);
  // prep.setString(2, password);
  // try (ResultSet rs = prep.executeQuery()) {
  // int count = rs.getInt(1);
  // if (count != 0) {
  // return true;
  // } else {
  // return false;
  // }
  // }
  // } catch (NullPointerException | SQLException n) {
  // n.printStackTrace();
  // return false;
  // }
  // }
}
