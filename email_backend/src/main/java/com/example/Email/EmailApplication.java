package com.example.Email;

import com.example.Email.Service.Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class EmailApplication {
	static Service service;

	@Autowired
	public EmailApplication(Service service) throws IOException, ParseException {
		this.service = service;
		service.loadDataBase();
	}

	public static void main(String[] args)  {

		SpringApplication.run(EmailApplication.class, args);
		Runtime.getRuntime().addShutdownHook(new Thread(()->{
			try {
				System.out.println("program ended");
				service.saveDataBase();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}));

//		Service ser=new Service();
//		Model data = Model.getInstance();
//		service.signUp("faris","12345");
//		User faris = data.getUser("faris");
//		service.signUp("ahmed","54321");
//
//		User ahmed = data.getUser("ahmed");
//		service.signUp("ahmed2","654321");
//
//		User ahmed2 = data.getUser("ahmed2");
//
//		Message m1 = new Message("16", "ahmed,ahmed2", "faris", 10, "kora", "hello");
//		System.out.println(m1.toJson());
		//		Message m2 = new Message("15", "ahmed,faris", "ahmed2", 5, "kora2", "hello2");
//		System.out.println(ser.printDataBase());
//		String z = m1.toJson();
//		ser.addMessages(z);
//		String z2 = m2.toJson();
//		ser.addMessages(z2);
//		ser.addFolder("faris", "myfolder");
//		ser.addFolder("faris", "myfolder2");
//		ser.deleteFolder("faris", "myfolder");
//		ser.renameFolder("faris", "myfolder2", "mymine");
//		ser.getperosn("faris").getCollection().set_al_folder("allmail", ser.sort("faris", "allmail", 2));
//		LinkedList<String> l = new LinkedList<>();
//		l.add("16");
//		l.add("15");
//		ser.moveMessages(l, "faris", "inbox", "allmail");
//		ser.delMessages(l, "ahmed", "allmail");
//		ser.moveMessages(l, "ahmed", "trash", "inbox");
//		ser.addMesDraft(z);
//
//
//		ser.addContact("faris","{\n" +
//				"            \"contactName\" : \"ahmed\",\n" +
//				"                \"emails\" : [ \"jhzgb@rkej.vfbjgn\", \"vsgb@sdvjgb.bjbsdvj\" ]\n" +
//				"        }");
//		Contact __ahmed__ = ser.searchContact("faris", "ahmed");
//		ser.addContact("faris","{\n" +
//				"            \"contactName\" : \"ali\",\n" +
//				"                \"emails\" : [ \"jhzgb@rkej.vfbjgn\", \"vsgb@sdvjgb.bjbsdvj\" ]\n" +
//				"        }");
//		ser.renameContact("faris","kono","ahmed");
//		ser.editContactinfo("faris","{\n" +
//				"            \"contactName\" : \"kono\",\n" +
//				"                \"emails\" : [ \"kimofathy@cfc.com\", \"vsgb@sdvjgb.bjbsdvj\" ]\n" +
//				"        }");
//		ser.deleteContact("faris","ali");
//
//
//
//
//
//
//
//
//	/*{
//		"contactName" : "name",
//			"emails" : [ "jhzgb@rkej.vfbjgn", "vsgb@sdvjgb.bjbsdvj" ]
//	}*/
//


	}
}
