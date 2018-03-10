package org.knowm.xchange.idex;

import org.apache.commons.codec.binary.Hex;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Sign;
import org.web3j.crypto.Sign.SignatureData;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.LinkedList;
import java.util.List;

import static java.lang.Integer.max;
import static java.lang.Integer.min;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Arrays.asList;
import static org.web3j.crypto.Hash.sha3;

public class IdexSignature {
    static Boolean debugMe = IdexMarketDataService.Companion.getDebugMe();

    static/** Generate v, r, s values from payload */
    SignatureData generateSignature(String apiSecret, List<List<String>> data) {
        byte[] rawhash = null;
        byte[] saltBytes;
        byte[] bytes = null;
        final String[] last = new String[1];
        BigInteger apiSecret1;
        ECKeyPair ecKeyPair;
        try (ByteArrayOutputStream sig_arr = new ByteArrayOutputStream()) {

            if (debugMe) {
                System.err.println(data);
            }
            for (List<String> d : data) {
                String data1 = d.get(1);
                /* remove 0x prefix and convert to bytes*/
                if (debugMe) System.err.println("\n===\nsignature  for (len: " + d.get(1).length() + " ):" + d);
                byte[] segment = new byte[0];
                byte[] r = new byte[0];
                last[0] = new LinkedList<>(asList(data1.toLowerCase().split("0x"))).getLast();
                switch (d.get(2)) {
                    case "address": {
                        segment = new byte[20];
                        r = new BigInteger(last[0], 16).toByteArray();

                        break;
                    }

                    case "uint256": {
                        segment = new byte[32];
                        r = new BigInteger(last[0], 10).toByteArray();

                        break;
                    }

                }
                int segLen = segment.length;
                int rlen = min(max(segLen, r.length), r.length);
                int oversize = r.length - segLen;
                System.arraycopy(r, oversize > 0 ? oversize : 0, segment, oversize > 0 ? 0 : (segLen - rlen), min(segLen, rlen));

                sig_arr.write(segment);

                if (debugMe) {
                    System.err.println("signature: results: (len: " + rlen + "->" + segLen + ") " + Hex.encodeHexString(segment));
                    System.err.println("signature: accumulated: " + Hex.encodeHexString(sig_arr.toByteArray()));
                }
            }
            rawhash = sha3(sig_arr.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // salt the hashed packed string
        saltBytes = "\u0019Ethereum Signed Message:\n32".getBytes();

        assert (Hex.encodeHexString(
                saltBytes).toLowerCase() == "19457468657265756d205369676e6564204d6573736167653a0a3332");

        byte[] salted=null;
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            byteArrayOutputStream.write(saltBytes);
            byteArrayOutputStream.write(rawhash);
            bytes = byteArrayOutputStream.toByteArray();
            salted = sha3(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (debugMe) {
            System.err.println("\n== unsalted:\t" + Hex.encodeHexString(rawhash));
            System.err.println("== salt raw :\t" + Hex.encodeHexString(saltBytes));
            System.err.println("== salted raw :\t" + Hex.encodeHexString(bytes));
            System.err.println("== salted hash:\t" + Hex.encodeHexString(salted));
        }
        last[0] = new LinkedList<String>(asList(apiSecret.split("0x"))).getLast();
        apiSecret1 = new BigInteger(last[0], 16);
        ecKeyPair = ECKeyPair.create(apiSecret1);


        return
                Sign.signMessage(
                        /* message = */salted,
                        /* keyPair=  */ ecKeyPair
                );
    }



}