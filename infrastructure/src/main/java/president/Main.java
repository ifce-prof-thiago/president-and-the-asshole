package president;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import president.application.usecase.room.create.CreateRoomIn;
import president.application.usecase.room.create.CreateRoomOut;
import president.application.usecase.room.create.CreateRoomUseCaseImpl;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {

        SpringApplication.run(Main.class, args);

    }

}
