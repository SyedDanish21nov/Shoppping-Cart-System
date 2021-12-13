package com.shopping.model;

	import org.springframework.data.annotation.Id;
	import org.springframework.data.mongodb.core.mapping.Document;

	@Document(collection = "Admin")
	public class AdminDetails {
		@Id
		private int admId;
		private String admName;
		private String admEmail;
		private String admPassword;
		
		public AdminDetails() {
			
		
		}

		public AdminDetails(int admId, String admName, String admEmail, String admPassword) {
			super();
			this.admId = admId;
			this.admName = admName;
			this.admEmail = admEmail;
			this.admPassword = admPassword;
		}

		public int getAdmId() {
			return admId;
		}

		public void setAdmId(int admId) {
			this.admId = admId;
		}

		public String getAdmName() {
			return admName;
		}

		public void setAdmName(String admName) {
			this.admName = admName;
		}

		public String getAdmEmail() {
			return admEmail;
		}

		public void setAdmEmail(String admEmail) {
			this.admEmail = admEmail;
		}

		public String getAdmPassword() {
			return admPassword;
		}

		public void setAdmPassword(String admPassword) {
			this.admPassword = admPassword;
		}

		@Override
		public String toString() {
			return "AdminDetails [admId=" + admId + ", admName=" + admName + ", admEmail=" + admEmail + ", admPassword="
					+ admPassword + "]";
		}
		
		

		
		
}
