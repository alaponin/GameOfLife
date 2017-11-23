package tests;

public class TestFactory {

    public Test getTestScenario(String testType){

        if(testType == null){
            return null;
        }
        if(testType.equalsIgnoreCase("blinker")){
            return new Blinker();

        } else if(testType.equalsIgnoreCase("toad")){
            return new Toad();

        } else if(testType.equalsIgnoreCase("beacon")){
            return new Beacon();

        } else if(testType.equalsIgnoreCase("pulsar")){
            return new Pulsar();
        }

        return null;
    }

}
