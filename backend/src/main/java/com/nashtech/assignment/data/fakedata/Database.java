package com.nashtech.assignment.data.fakedata;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.nashtech.assignment.data.constants.EAssetStatus;
import com.nashtech.assignment.data.constants.EAssignStatus;
import com.nashtech.assignment.data.constants.EGender;
import com.nashtech.assignment.data.constants.EUserType;
import com.nashtech.assignment.data.entities.Asset;
import com.nashtech.assignment.data.entities.AssignAsset;
import com.nashtech.assignment.data.entities.Category;
import com.nashtech.assignment.data.entities.User;
import com.nashtech.assignment.data.repositories.AssetRepository;
import com.nashtech.assignment.data.repositories.AssignAssetRepository;
import com.nashtech.assignment.data.repositories.CategoryRepository;
import com.nashtech.assignment.data.repositories.UserRepository;

@Configuration
public class Database {
        @Bean
        CommandLineRunner initDatabase(UserRepository userRepository, CategoryRepository categoryRepository,
                        AssignAssetRepository assignRepository, AssetRepository assetRepository) {
                return new CommandLineRunner() {

                        @Override
                        public void run(String... args) throws Exception {
                                Date date = new Date();
                                int numberOfUser = 35;
                                Random random = new Random();
                                String[] firstNames = { "Kim", "Mai", "Thu", "Nguyệt", "Ngọc", "Ðiệp",
                                                "Diệu", "Hằng", "Vân", "Thúy", "Thanh", "Trúc", "Mỹ", "Nga", "Diễm",
                                                "My",
                                                "Hà", "Liên", "Kiều", "Loan", "Phương", "Mai", "Vân", "Trinh",
                                                "Vân", "Nhi", "Hoài", "Trang", "Ðài", "Trang", "Thục", "Trinh", "Thu",
                                                "Ngân", "Quỳnh", "Giang",
                                                "Bạch", "Tuyết", "Ánh", "Mai" };
                                String[] lastNames = { "Kiều Trang", "Hoàng Hà", "Lê Khánh", "Dư", "Cổ", "Tiêu", "Tiết",
                                                "Đoàn Thanh", "Đỗ Minh",
                                                "Trương Công Thành", "Tiền Lê Khoáng", "Hồ Ngọc", "Tạ", "Hứa", "Đinh",
                                                "Uông", "Hùng", "Giang",
                                                "Chu", "Hàn", "Bác", "Nguỵ", "Điền", "Kim", "Sử", "Lục", "Nhâm",
                                                "Thẩm", "Đường	Cao", "Lưu", "Lâm", "Phùng", "Tăng", "Diệp", "Dương", };
                                List<EGender> gender = new ArrayList<>();
                                gender.add(EGender.FEMALE);
                                gender.add(EGender.MALE);
                                gender.add(EGender.OTHERS);
                                EUserType[] types = { EUserType.ADMIN, EUserType.STAFF };

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
                                                        .username(firstName + "@" + String.valueOf(randomLastName))
                                                        .firstName(firstName)
                                                        .lastName(lastNames[randomLastName])
                                                        .gender(gender.get(randomGender))
                                                        .location("HCM")
                                                        .staffCode("SD000" + String.valueOf(i + 1))
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

                                Category category = Category.builder().name("Laptop")
                                                .prefixAssetCode("LP")
                                                .build();
                                categoryRepository.save(category);

                                String[] laptops = { "Lenovo ThinkPad X1 Carbon Gen ", "Lenovo ThinkPad X13 AMD ",
                                                "MSI Pulse GL66 11UGK-869 ","ASUS ExpertBook B3 Flip ","MacBook Pro ",
                                                "MSI GS66 Stealth ", "ASUS Chromebook Flip CX5", "MacBook Air " };
                                List<Asset> assets = new ArrayList<>();
                                int numberAssert = 50;
                                List<EAssignStatus> eAssignStatus = new ArrayList<>();
                                eAssignStatus.add(EAssignStatus.ACCEPTED);
                                eAssignStatus.add(EAssignStatus.WAITING_FOR_ACCEPTANCE);
                                eAssignStatus.add(EAssignStatus.DECLINED);
                                for (int i = 0; i < numberAssert; i++) {
                                        int randomLaptopName = random.nextInt(laptops.length);
                                        
                                        Asset asset = Asset.builder()
                                                        .assetCode("LP000" + String.valueOf(i))
                                                        .category(category)
                                                        .installedDate(date)
                                                        .isDeleted(false)
                                                        .location("HCM")
                                                        .name(laptops[randomLaptopName] + String.valueOf(i))
                                                        .status(EAssetStatus.AVAILABLE)
                                                        .build();
                                        assets.add(asset);
                                }
                                assetRepository.saveAll(assets);
                                int numberOfAssignAsset = 70;
                                List<AssignAsset> assignAssets = new ArrayList<>();
                                for (int i = 0; i < numberOfAssignAsset; i++) {
                                        int randomAsset = random.nextInt(assets.size());
                                        int randomUser = random.nextInt(users.size());
                                        int randomEAssignStatus = random.nextInt(eAssignStatus.size());
                                        AssignAsset assignAsset = AssignAsset.builder()
                                                        .asset(assets.get(randomAsset))
                                                        .isDeleted(false)
                                                        .status(eAssignStatus.get(randomEAssignStatus))
                                                        .assignedDate(date)
                                                        .note("Something")
                                                        .userAssignedBy(rootUser)
                                                        .userAssignedTo(users.get(randomUser))
                                                        .build();
                                        assignAssets.add(assignAsset);
                                }
                                assignRepository.saveAll(assignAssets);

                        }

                };
        };
}
