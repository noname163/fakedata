package com.nashtech.assignment.data.fakedata;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.nashtech.assignment.data.constants.EGender;
import com.nashtech.assignment.data.constants.EUserType;
import com.nashtech.assignment.data.entities.User;
import com.nashtech.assignment.data.repositories.UserRepository;

@Configuration
public class Database {
        @Bean
        CommandLineRunner initDatabase(UserRepository userRepository) {
                return new CommandLineRunner() {

                        @Override
                        public void run(String... args) throws Exception {
                                Date date = new Date();
                                int numberOfUser = 35;
                                Random random = new Random();
                                String[] firstNames = { "Kim", "Mai","Thu", "Nguyệt","Ngọc", "Ðiệp",
                                                "Diệu", "Hằng","Vân", "Thúy","Thanh", "Trúc","Mỹ", "Nga","Diễm", "My",
                                                "Hà", "Liên","Kiều", "Loan","Phương", "Mai","Vân", "Trinh",
                                                "Vân", "Nhi","Hoài", "Trang","Ðài", "Trang","Thục", "Trinh","Thu", "Ngân","Quỳnh", "Giang",
                                                "Bạch", "Tuyết","Ánh", "Mai" };
                                String[] lastNames = {"Kiều Trang",	"Hoàng Hà",	"Lê Khánh",	"Dư",	"Cổ",	"Tiêu",	"Tiết",	"Đoàn Thanh",	"Đỗ Minh",
                                        "Trương Công Thành",	"Tiền Lê Khoáng",	"Hồ Ngọc",	"Tạ",	"Hứa",	"Đinh",	"Uông",	"Hùng",	"Giang",
                                        "Chu",	"Hàn",	"Bác",	"Nguỵ",	"Điền",	"Kim",	"Sử",	"Lục",	"Nhâm",
                                        "Thẩm",	"Đường	Cao",	"Lưu",	"Lâm",	"Phùng",	"Tăng",	"Diệp",	"Dương",};
                                List<EGender> gender = new ArrayList<>();
                                gender.add(EGender.FEMALE);
                                gender.add(EGender.MALE);
                                gender.add(EGender.OTHERS);
                                EUserType[] types = {EUserType.ADMIN, EUserType.STAFF};
                                
                                
                                List<User> users = new ArrayList<>();

                                for (int i = 0; i < numberOfUser; i++) {
                                        int randomFirstName = random.nextInt(firstNames.length);
                                int randomLastName = random.nextInt(lastNames.length);
                                int randomGender = random.nextInt(gender.size());
                                int randomType = random.nextInt(types.length);
                                String firstName = firstNames[randomFirstName];
                                        User user = User.builder()
                                                .dateOfBirth(date)
                                                .joinedDate(date)
                                                .type(types[randomType])
                                                .username(firstName+ "@"+String.valueOf(randomLastName))
                                                .firstName(firstName)
                                                .lastName(lastNames[randomLastName])
                                                .gender(gender.get(randomGender))
                                                .location("HCM")
                                                .staffCode("SD000" + String.valueOf(i+1))
                                                .password("123456")
                                                .build();
                                        users.add(user);
                                }

                                User rootUser = User.builder().firstName("Dat").lastName("Huu Dang")
                                .username("DatDH")
                                .password("$2a$12$Bxe13JvaTI9WCs8F9L01GOQNdoXZ4IHcD0Ug4AEPrJgwn3JmeCmM2")
                                .dateOfBirth(date)
                                .staffCode("SD0000")
                                .type(EUserType.ADMIN)
                                .gender(EGender.MALE)
                                .location("HCM")
                                .joinedDate(date).build();
                                users.add(rootUser);
                                userRepository.saveAll(users);
                        }

                };
        }
}
