/**
 */
class Animal {
}

/**
 */
class Dog extends Animal {
}

/**
 */
class Cat extends Animal {
}

/**
 */
class Vet {
    /**
     * Method main.
     * @param args String[]
     */
    public static void main(String[] args) {
        Animal[] aa = {new Dog(), new Dog(), new Dog()};
        for (Object o : aa) {
            goWalk((Dog) o);
        }
    }

    /**
     * Method goWalk.
     * @param d Dog
     */
    static void goWalk(Dog d) {
    }
}