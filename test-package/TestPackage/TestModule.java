package TestPackage;

import TestPackage.OtherModule;

class TestModule {
    public static void main(String[] args) {
        System.out.println("Hello from main()");
        OtherModule.greet();
    }
}
