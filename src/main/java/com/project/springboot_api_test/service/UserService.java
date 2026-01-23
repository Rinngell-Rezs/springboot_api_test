package com.project.springboot_api_test.service;

import com.project.springboot_api_test.api.model.Address;
import com.project.springboot_api_test.api.model.User;
import com.project.springboot_api_test.helper.PasswordHelper;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

import static com.project.springboot_api_test.helper.DateHelper.DATE_FORMAT;
import static com.project.springboot_api_test.helper.DateHelper.MADAGASCAR_TZ;


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
                "AARR990102XXX",
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
                "AARR990103XXX",
                ZonedDateTime.now(MADAGASCAR_TZ).format(DATE_FORMAT),
                ( new ArrayList<Address>(Arrays.asList(
                        new Address(1, "workaddress", "street No. 6", "US"),
                        new Address(2, "homeaddress", "street No. 8", "NZ")
                )))
            )
        );
    }

}
