package fun.zjlzjl.servicezuul.DTO;

import fun.zjlzjl.servicezuul.bean.Role;

import java.util.List;

public class userDto {
        private static final long serialVersionUID = 1L;

        private Integer Id;

        private String userName;

        private String passWord;

        private Integer userId;

        private Boolean enabled;
        private String nickName;

        private String qq;

        private String email;

        private String avatar;

        private String regTime;
        private List<Role> roles;

        public List<Role> getRoles() {
            return roles;
        }

        public void setRoles(List<Role> roles) {
            this.roles = roles;
        }

        public static long getSerialVersionUID() {
            return serialVersionUID;
        }


        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getPassWord() {
            return passWord;
        }

        public void setPassWord(String passWord) {
            this.passWord = passWord;
        }

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getRegTime() {
            return regTime;
        }

        public void setRegTime(String regTime) {
            this.regTime = regTime;
        }
        public Boolean getEnabled() {
            return enabled;
        }

        public void setEnabled(Boolean enable) {
            this.enabled = enable;
        }

    }
