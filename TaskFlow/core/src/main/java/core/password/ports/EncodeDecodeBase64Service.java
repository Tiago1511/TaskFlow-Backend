package core.password.ports;

public interface EncodeDecodeBase64Service {

    String encode(String input);

    String decode(String encodedInput);

}
