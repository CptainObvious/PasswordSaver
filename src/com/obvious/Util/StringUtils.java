package com.obvious.Util;

/*
Licensed to the Apache Software Foundation (ASF) under one
or more contributor license agreements.  See the NOTICE file
distributed with this work for additional information
regarding copyright ownership.  The ASF licenses this file
to you under the Apache License, Version 2.0 (the
"License"); you may not use this file except in compliance
with the License.  You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing,
software distributed under the License is distributed on an
"AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
KIND, either express or implied.  See the License for the
specific language governing permissions and limitations
under the License.    
*/

//package org.opentides.util;

import java.io.IOException;
import java.util.Date;
import java.util.Random;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class StringUtils {

    private static Random random = new Random((new Date()).getTime());
    /**
     * Encrypts the string along with salt 
     * @param userId
     * @return
     * @throws Exception
     */
    public static String encrypt(String userId) {
        BASE64Encoder encoder = new BASE64Encoder();

        // let's create some dummy salt
        byte[] salt = new byte[8];
        random.nextBytes(salt);
        return encoder.encode(salt) +
            encoder.encode(userId.getBytes());
    }


    /**
     * Decrypts the string and removes the salt
     * @param encryptKey
     * @return
     * @throws Exception
     */
    public static String decrypt(String encryptKey) {
        // let's ignore the salt
        if (encryptKey.length() > 12) {
            String cipher = encryptKey.substring(12);
            BASE64Decoder decoder = new BASE64Decoder();
            try {
                return new String(decoder.decodeBuffer(cipher));
            } catch (IOException e) {
                //  throw new InvalidImplementationException(
                //    "Failed to perform decryption for key ["+encryptKey+"]",e);
            }
        }
        return null;
    }

}