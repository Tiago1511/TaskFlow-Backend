package core.user.ports;

public interface EncoderService {

    String encode(String rawPassword);

    boolean matches(String rawPassword, String encodedPassword);
}
