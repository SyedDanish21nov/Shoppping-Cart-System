package com.shopping.service;

	import java.util.ArrayList;
	import java.util.List;

	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.security.core.userdetails.UserDetails;
	import org.springframework.security.core.userdetails.UserDetailsService;
	import org.springframework.stereotype.Service;

	import com.shopping.model.AdminDetails;
	import com.shopping.repository.AdminRepository;
	import org.springframework.security.core.userdetails.User;
	import org.springframework.security.core.userdetails.UsernameNotFoundException;

	@Service
	public class AdminService implements UserDetailsService{
		
		@Autowired
		private AdminRepository AdminRepository;
		
		@Override
		public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AdminDetails foundedAdmin = AdminRepository.findByAdmEmail(username);
		if (foundedAdmin ==null) return null;
		String aEmail = foundedAdmin.getAdmEmail();
		String aPassword = foundedAdmin.getAdmPassword();
		return new User(aEmail, aPassword, new ArrayList<>());
		}


		
		//for creating/adding Customer
		public AdminDetails addAdmin(AdminDetails AdminDetails) {
			
			return AdminRepository.save(AdminDetails);
		}

		public List<AdminDetails> getAdminDetails() {
			
			List<AdminDetails> AdminDetails =  AdminRepository.findAll();
			System.out.println("Getting Admins from DB" + AdminDetails);
			return AdminDetails;
		}
		
			//For deleting By Id
			public void deleteById(int id) {
				AdminRepository.deleteById(id);
				
		
	}

}
