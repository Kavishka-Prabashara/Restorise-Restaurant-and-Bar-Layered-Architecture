
package lk.restorise.restorisems.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter

@NoArgsConstructor
public class UserDTO {

   private String email;
   private String username;
   private String password;
   private String cmbbxQuestion;
   private String answer;


   public UserDTO(String email, String username, String password, String cmbbxQuestion, String txtanswer) {
      this.email = email;
      this.username = username;
      this.password = password;
      this.cmbbxQuestion = cmbbxQuestion;
      this.answer = txtanswer;
   }

   public String getTxtemail() {
      return email;
   }

   public void setTxtemail(String txtemail) {
      this.email = txtemail;
   }

   public String getTxtusername() {
      return username;
   }

   public void setTxtusername(String txtusername) {
      this.username = txtusername;
   }


}
