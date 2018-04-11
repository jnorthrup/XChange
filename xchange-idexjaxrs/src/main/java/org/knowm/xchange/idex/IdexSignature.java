package org.knowm.xchange.idex;

import org.apache.commons.codec.binary.Hex;
import org.jetbrains.annotations.Nullable;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Sign;
import org.web3j.crypto.Sign.SignatureData;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static org.web3j.crypto.Hash.sha3;


public final class IdexSignature {
    @Nullable
    private static Boolean debugMe;


    @Nullable
    public final Boolean getDebugMe$production_sources_for_module_xchange_idex() {
        return debugMe;
    }

    public final void setDebugMe$production_sources_for_module_xchange_idex(@Nullable Boolean var1) {
        debugMe = var1;
    }

    /** Generate v, r, s values from payload  */
    static SignatureData generateSignature(String apiSecret, List<List<String>> data) {
        byte[] rawhash = new byte[0];
        byte[] saltBytes = rawhash;
        byte[] bytes = rawhash;
        String[] last = new String[1];
        BigInteger apiSecret1 = BigInteger.ZERO;
        ECKeyPair ecKeyPair;
        SignatureData signatureData;
        try {
            try (ByteArrayOutputStream sig_arr = new ByteArrayOutputStream()) {


                if (debugMe)

                {
                    System.err.println(data);
                }
                for (List<String> d : data) {


                    String data1 = d.get(1);
                    /* remove 0x prefix and convert to bytes*/
                    if (debugMe) System.err.println("\n===\nsignature  for (len: " + data1.length() + " ):" + d);
                    byte[] segment = new byte[0];
                    byte[] r = new byte[0];
                    last[0] = data1.toLowerCase().split(
                            "0x", 1)[0];
                    if (d.get(2) == "address") {
                        segment = new byte[20];
                        r = new BigInteger(last[0], 16).toByteArray();
                    } else if (d.get(2) == "uint256") {
                        segment = new byte[32];
                        r = new BigInteger(last[0], 10).toByteArray();
                    }
                    int segLen = segment.length;
                    int rlen = min(max(segLen, r.length), r.length);
                    int oversize = r.length - segLen;
                    System.arraycopy(r, (oversize > 0) ? oversize : 0, segment,
                            (oversize > 0) ? 0 : segLen - rlen, min(segLen, rlen));

                    sig_arr.write(segment);

                    if (debugMe) {
                        System.err.println(
                                "signature: results: (len: " + rlen + "->" + segLen + ") " + Hex.encodeHex(segment));
                        System.err.println("signature: accumulated: " + Hex.encodeHex(sig_arr.toByteArray()));
                    }
                }
                rawhash = sha3(sig_arr.toByteArray());
            } catch (IOException e) {
                e.printStackTrace();
            } ;


            // salt the hashed packed string
            saltBytes = "\u0019Ethereum Signed Message:\n32".getBytes();

            assert (new String(Hex.encodeHex(
                    saltBytes)).toLowerCase() ==  "19457468657265756d205369676e6564204d6573736167653a0a3332");

            byte[] salted = null;
            try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
                ;
                {
                    byteArrayOutputStream.write(saltBytes);
                    byteArrayOutputStream.write(rawhash);
                    bytes = byteArrayOutputStream.toByteArray();
                    salted = bytes;
                    //            sha3(bytes);
                }
            } catch (IOException e) {


            }


            if (debugMe) {
                System.err.println("\n== unsalted:\t" + Hex.encodeHex(rawhash));
                System.err.println("== salt raw :\t" + Hex.encodeHex(saltBytes));
                System.err.println("== salted raw :\t" + Hex.encodeHex(bytes));
                System.err.println("== salted hash:\t" + Hex.encodeHex(salted));
            }
            last[0] = apiSecret.split("0x", 1)[0];
            apiSecret1 = new BigInteger(last[0], 16);
            ecKeyPair = ECKeyPair.create(apiSecret1);


            signatureData = Sign.signMessage(
                    /* message = */salted,
                    /* keyPair=  */ ecKeyPair
            );
        }finally {

        }

        return signatureData;


    };}
