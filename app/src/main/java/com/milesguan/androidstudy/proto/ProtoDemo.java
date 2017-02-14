package com.milesguan.androidstudy.proto;

import com.milesguan.androidstudy.proto.AddressBookProtos.Person;

/**
 * Created by renjieguan on 17/2/13.
 */

public class ProtoDemo {

    public static final byte[] serializeTest() {
        Person john =
                Person.newBuilder()
                        .setId(1234)
                        .setName("John Doe")
                        .setEmail("jdoe@example.com")
                        .addPhones(
                                Person.PhoneNumber.newBuilder()
                                        .setNumber("555-4321")
                                        .setType(Person.PhoneType.HOME))
                        .build();
        byte[] bytes = john.toByteArray();
        return bytes;
    }


}
