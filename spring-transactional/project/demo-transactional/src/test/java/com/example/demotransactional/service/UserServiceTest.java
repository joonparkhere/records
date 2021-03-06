package com.example.demotransactional.service;

import com.example.demotransactional.domain.Orders;
import com.example.demotransactional.domain.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    DatabaseCleanUp databaseCleanUp;

    User setUpUser;

    @BeforeEach
//    @Transactional
    void setUp() {
        setUpUser = userService.save("set-up-nickname", 20);
    }

    @AfterEach
    void tearDown() {
        databaseCleanUp.truncateAllEntity();
    }

    @Test
    @DisplayName("Non Transactional")
    void changeNicknameTest() {
        String nickname = "sample-nickname";
        int age = 20;
        User savedUser = userService.save(nickname, age);

        /*
        * INSERT USER
        * */

        String newNickname = "new-nickname";
        User findUser = userService.changeNickname(savedUser.getId(), newNickname);

        /*
        * UPDATE USER
        * */

        /*
        * savedUser = {User@9086}
        *   id = 9
        *   nickname = "sample-nickname"
        *   age = 20
        *
        * findUser = {User@9146}
        *   id = 9
        *   nickname = "new-nickname"
        *   age = 20
        * */

        assertEquals(newNickname, findUser.getNickname());
        assertNotEquals(newNickname, savedUser.getNickname());
        assertEquals(findUser.getId(), savedUser.getId());
        assertNotEquals(findUser, savedUser);

        /*
        * No Rollback
        * */
    }

    @Test
    @DisplayName("Propagation: Required (?????? ?????? ???. ?????? ??????????????? ???????????? ????????? ???????????????, ???????????? ???????????? ????????? ??????????????? ??????)")
    @Transactional
    void changeNicknameAsRequiredTransactionalTest() {
        String nickname = "sample-nickname";
        int age = 20;
        User savedUser = userService.save(nickname, age);

        /*
        * No INSERT USER
        * */

        String newNickname = "new-nickname";
        User findUser = userService.changeNickname(savedUser.getId(), newNickname);

        /*
        * No UPDATE USER
        * */

        /*
        * savedUser = {User@9055}
        *   id = 11
        *   nickname = "new-nickname"
        *   age = 20
        *
        * findUser = {User@9055}
        *   id = 11
        *   nickname = "new-nickname"
        *   age = 20
        * */

        assertEquals(findUser, savedUser);

        /*
        * Rollback Well
        * */
    }

    @Test
    @DisplayName("Propagation: Supports (?????? ??????????????? ???????????? ????????? ???????????????, ???????????? ???????????? ?????? ???????????? ?????? Non-???????????? ????????? ??????)")
    @Transactional(propagation = Propagation.SUPPORTS)
    void changeNicknameAsSupportsTransactionalTest() {
        String nickname = "sample-nickname";
        int age = 20;
        User savedUser = userService.save(nickname, age);

        /*
        * INSERT USER
        * */

        String newNickname = "new-nickname";
        User findUser = userService.changeNickname(savedUser.getId(), newNickname);

        /*
        * UPDATE USER
        * */

        /*
        * savedUser = {User@9086}
        *   id = 6
        *   nickname = "sample-nickname"
        *   age = 20
        *
        * findUser = {User@9151}
        *   id = 6
        *   nickname = "new-nickname"
        *   age = 20
        * */

        assertEquals(newNickname, findUser.getNickname());
        assertNotEquals(newNickname, savedUser.getNickname());
        assertEquals(findUser.getId(), savedUser.getId());
        assertNotEquals(findUser, savedUser);

        /*
        * No Rollback
        * */
    }

    /*
    * ?????? ??????????????? ???????????? ????????? ???????????????, ???????????? ???????????? ????????? ?????????.
    * */
    @Test
    @DisplayName("Propagation - Mandatory")
    @Transactional(propagation = Propagation.MANDATORY)
    void changeNicknameAsMandatoryTransactionalTest() {
        /*
        * IllegalTransactionStateException ??????
        * */
        System.out.println("?????? ???????");
    }

    @Test
    @DisplayName("Propagation: Requires New (?????? ??????????????? ???????????? ????????????, ????????? ??????????????? ??????)")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    void changeNicknameAsRequiresNewTransactionalTest() {
        String nickname = "sample-nickname";
        int age = 20;
        User savedUser = userService.save(nickname, age);

        /*
        * No INSERT USER
        * */

        String newNickname = "new-nickname";
        User findUser = userService.changeNickname(savedUser.getId(), newNickname);

        /*
        * No UPDATE USER
        * */

        /*
        * savedUser = {User@9055}
        *   id = 8
        *   nickname = "new-nickname"
        *   age = 20
        *
        * findUser = {User@9055}
        *   id = 8
        *   nickname = "new-nickname"
        *   age 20
        * */

        assertEquals(findUser, savedUser);

        /*
        * Rollback Well
        * */
    }

    @Test
    @DisplayName("Propagation: Not Supported (?????? ??????????????? ???????????? ???????????????, Non-???????????? ????????? ??????)")
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    void changeNicknameAsNotSupportedTransactionalTest() {
        String nickname = "sample-nickname";
        int age = 20;
        User savedUser = userService.save(nickname, age);

        /*
         * INSERT USER
         * */

        String newNickname = "new-nickname";
        User findUser = userService.changeNickname(savedUser.getId(), newNickname);

        /*
         * UPDATE USER
         * */

        /*
         * savedUser = {User@9086}
         *   id = 15
         *   nickname = "sample-nickname"
         *   age = 20
         *
         * findUser = {User@9145}
         *   id = 15
         *   nickname = "new-nickname"
         *   age = 20
         * */

        assertEquals(newNickname, findUser.getNickname());
        assertNotEquals(newNickname, savedUser.getNickname());
        assertEquals(findUser.getId(), savedUser.getId());
        assertNotEquals(findUser, savedUser);

        /*
         * No Rollback
         * */
    }

    @Test
    @DisplayName("Propagation: Never (?????? ??????????????? ???????????? ????????? ?????????, Non-???????????? ????????? ??????)")
    @Transactional(propagation = Propagation.NEVER)
    void changeNicknameAsNeverTransactionalTest() {
        String nickname = "sample-nickname";
        int age = 20;
        User savedUser = userService.save(nickname, age);

        /*
         * INSERT USER
         * */

        String newNickname = "new-nickname";
        User findUser = userService.changeNickname(savedUser.getId(), newNickname);

        /*
         * UPDATE USER
         * */

        /*
         * savedUser = {User@9086}
         *   id = 16
         *   nickname = "sample-nickname"
         *   age = 20
         *
         * findUser = {User@9145}
         *   id = 16
         *   nickname = "new-nickname"
         *   age = 20
         * */

        assertEquals(newNickname, findUser.getNickname());
        assertNotEquals(newNickname, savedUser.getNickname());
        assertEquals(findUser.getId(), savedUser.getId());
        assertNotEquals(findUser, savedUser);

        /*
         * No Rollback
         * */
    }

    @Test
    @DisplayName("Propagation: Nested (?????? ??????????????? ???????????? ????????? ??????????????? ???????????? ????????????, ?????? ??????????????? ???????????? ????????? Required??? ???????????? ??????)")
    @Transactional(propagation = Propagation.NESTED)
    void changeNicknameAsNestedTransactionalTest() {
        String nickname = "sample-nickname";
        int age = 20;
        User savedUser = userService.save(nickname, age);

        /*
         * No INSERT USER
         * */

        String newNickname = "new-nickname";
        User findUser = userService.changeNickname(savedUser.getId(), newNickname);

        /*
         * No UPDATE USER
         * */

        /*
         * savedUser = {User@9055}
         *   id = 17
         *   nickname = "new-nickname"
         *   age = 20
         *
         * findUser = {User@9055}
         *   id = 17
         *   nickname = "new-nickname"
         *   age 20
         * */

        assertEquals(findUser, savedUser);

        /*
         * Rollback Well
         * */
    }

    @Test
//    @Transactional
    void orderAsLazyLoadingTest() {
        User orderedUser = userService.orderAsLazyLoading(setUpUser.getId(), "sample-item");

        List<Orders> ordersList = orderedUser.getOrdersList();
        for (Orders orders : ordersList) {
            System.out.println("order = " + orders);
        }
    }

    @Test
    void orderAsFetchJoinTest() {
        User orderedUser = userService.orderAsFetchJoin(setUpUser.getId(), "sample-item");

        List<Orders> ordersList = orderedUser.getOrdersList();
        for (Orders orders : ordersList) {
            System.out.println("order = " + orders);
        }
    }

}