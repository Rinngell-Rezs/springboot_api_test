package com.project.springboot_api_test.service;

import com.project.springboot_api_test.api.model.Address;
import com.project.springboot_api_test.api.model.User;
import com.project.springboot_api_test.dto.Filter;
import com.project.springboot_api_test.dto.Attribute;
import com.project.springboot_api_test.dto.LoginRequest;
import com.project.springboot_api_test.dto.UpdateUserRequest;
import com.project.springboot_api_test.helper.PasswordHelper;
import com.project.springboot_api_test.helper.PhoneHelper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.nio.channels.IllegalSelectorException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.project.springboot_api_test.helper.DateHelper.DATE_FORMAT;
import static com.project.springboot_api_test.helper.DateHelper.MADAGASCAR_TZ;
import static com.project.springboot_api_test.helper.TaxIdHelper.isValidTaxId;


@Service
public class UserService {

    private final ArrayList<User> userList;

    public UserService() {
        userList = new ArrayList<User>();
        userList.add(new User(
                UUID.randomUUID().toString(),
                "user1@mail.com",
                "user1",
                "+1 55 555 555 55",
                PasswordHelper.encrypt("password1"),
                "AARR990101XXX",
                ZonedDateTime.now(MADAGASCAR_TZ).format(DATE_FORMAT),
                ( new ArrayList<Address>(Arrays.asList(
                    new Address(1, "workaddress", "street No. 1", "UK"),
                    new Address(2, "homeaddress", "street No. 2", "AU")
                )))
            )
        );

        userList.add(new User(
                UUID.randomUUID().toString(),
                "user2@mail.com",
                "user2",
                "+1 55 567 890 00",
                PasswordHelper.encrypt("password2"),
                "ABCD000101XYZ",
                ZonedDateTime.now(MADAGASCAR_TZ).format(DATE_FORMAT),
                ( new ArrayList<Address>(Arrays.asList(
                        new Address(1, "workaddress", "street No. 1", "UK"),
                        new Address(2, "homeaddress", "street No. 3", "DE")
                )))
            )
        );

        userList.add(new User(
                UUID.randomUUID().toString(),
                "user3@mail.com",
                "user3",
                "+1 55 789 012 11",
                PasswordHelper.encrypt("password3"),
                "XYZW991231ABC",
                ZonedDateTime.now(MADAGASCAR_TZ).format(DATE_FORMAT),
                ( new ArrayList<Address>(Arrays.asList(
                        new Address(1, "workaddress", "street No. 6", "US"),
                        new Address(2, "homeaddress", "street No. 8", "NZ")
                )))
            )
        );
    }

    private Boolean taxIdExists(String taxId) {
        return userList.stream().anyMatch(user -> user.getTax_id().equals(taxId));
    }


    public ArrayList<User> getUsers(Attribute sortedBy, Filter filter) {
        // Filtering
        ArrayList<User> filteredUsers = switch (filter.getComparison()) {
            case CO ->
                    userList.stream().filter(user -> filter.getKey().extract(user).contains(filter.getQuery())).collect(Collectors.toCollection(ArrayList::new));
            case EQ ->
                    userList.stream().filter(user -> filter.getKey().extract(user).equals(filter.getQuery())).collect(Collectors.toCollection(ArrayList::new));
            case SW ->
                    userList.stream().filter(user -> filter.getKey().extract(user).startsWith(filter.getQuery())).collect(Collectors.toCollection(ArrayList::new));
            case EW ->
                    userList.stream().filter(user -> filter.getKey().extract(user).endsWith(filter.getQuery())).collect(Collectors.toCollection(ArrayList::new));
        };

        // Sorting
        if (sortedBy != null) {
            filteredUsers.sort((u1, u2) -> sortedBy.getKey().extract(u1).compareToIgnoreCase(sortedBy.getKey().extract(u2)));
            return filteredUsers;
        } else {
            return filteredUsers;
        }
    }

    public User createUser(User user) {
        if (taxIdExists(user.getTax_id())) {
            throw new IllegalArgumentException("Tax ID already exists: " + user.getTax_id());
        }
        if(!isValidTaxId(user.getTax_id())) {
            throw new IllegalArgumentException("Invalid tax ID format: " + user.getTax_id());
        }
        if(!PhoneHelper.isValidPhoneNumber(user.getPhone())) {
            throw new IllegalArgumentException("Invalid phone number format: " + user.getPhone());
        }
        user.setId(UUID.randomUUID().toString());
        user.setPassword(PasswordHelper.encrypt(user.getPassword()));
        user.setCreated_at(ZonedDateTime.now(MADAGASCAR_TZ).format(DATE_FORMAT));
        userList.add(user);
        return user;
    }

    public User updateUser(String id, UpdateUserRequest updateUserRequest) {
        Attribute attribute = updateUserRequest.getAttribute();
        String new_value = updateUserRequest.getNew_value();
        userList.stream()
            .filter(user -> user.getId().equals(id))
            .findFirst()
            .ifPresent(user -> {
                switch (attribute.getKey()) {
                    case EMAIL -> user.setEmail(new_value);
                    case NAME -> user.setName(new_value);
                    case PHONE -> user.setPhone(new_value);
                    case TAX_ID -> {
                        if (taxIdExists(new_value)) {
                            throw new IllegalArgumentException("Tax ID already exists: " + new_value);
                        }
                        user.setTax_id(new_value);
                    }
                    default -> throw new IllegalCallerException("Cannot update attribute: " + attribute.getKey());
                }
            });
        User user =  userList.stream()
            .filter(usr -> usr.getId().equals(id))
            .findFirst()
            .orElse(null);
        if (user == null) {
            throw new StringIndexOutOfBoundsException("User with ID " + id + " does not exist.");
        } else {
            return user;
        }
    }

    public ResponseEntity<String> deleteUser(String id) {
        if(!userList.removeIf(user -> user.getId().equals(id))){
            throw new StringIndexOutOfBoundsException("User with ID " + id + " does not exist.");
        }

        return ResponseEntity.ok("User with ID " + id + " has been deleted successfully.");
    }

    public User loginUser(LoginRequest loginRequest) {
        String taxId = loginRequest.getTax_id();
        String password = loginRequest.getPassword();
        User user = userList.stream()
            .filter(usr -> usr.getTax_id().equals(taxId) && PasswordHelper.comparePassword(password, usr.getPassword()))
            .findFirst()
            .orElse(null);
        if (user == null) {
            throw new StringIndexOutOfBoundsException("Password is incorrect or Tax ID does not exist: " + taxId);
        }
        return user;
    }
}
