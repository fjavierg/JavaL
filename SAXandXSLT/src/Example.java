import java.io.FileNotFoundException;

public interface Example {
  public void doTransform(String _inputFileName, String _transformerFileName, String _outputFileName) throws FileNotFoundException;
}