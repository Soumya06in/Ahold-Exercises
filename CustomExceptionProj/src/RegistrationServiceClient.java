public class RegistrationServiceClient {
	public static void main(String[] args) {
		RegistrationService service = new RegistrationService();
		try {
			service.validateEmail("abc@gmail1.com");
		} catch(DomainNotValidException de) {
			System.out.println("Catching custom exception DomainNotValidException");
			de.printStackTrace();
		}
	}
}
