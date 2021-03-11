package ejbholidaybookingapp;

import java.io.Serializable;

public class RoleDTO implements Serializable  {

		private static final long serialVersionUID = 1L;

		private int idRole;
		private String roleName;
		private int isAdmin;
		

		public RoleDTO(int idRole, String roleName, int isAdmin) {
			this.idRole = idRole;
			this.roleName = roleName;
			this.isAdmin = isAdmin;
		}

		public int getIdRole() {
			return idRole;
		}

		public void setIdRole(int idRole) {
			this.idRole = idRole;
		}

		public String getRoleName() {
			return roleName;
		}

		public void setRoleName(String roleName) {
			this.roleName = roleName;
		}

		public int getIsAdmin() {
			return isAdmin;
		}

		public void setIsAdmin(int isAdmin) {
			this.isAdmin = isAdmin;
		}

	}

