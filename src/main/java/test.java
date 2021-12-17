public class test {

    //get kafka auto sych control manually
    // them use after all processing commitSync - this will ensure all the work is compled and
    // then issue commit, Else retry from previous commit.
    //Take manual control for improving consistency and atomicity.

    private static test lazyInitialization = null;

    public static void getInstance() {


    }

    private test test() {

        if (lazyInitialization == null) {

            synchronized (test.class) {

                if (lazyInitialization == null) {

                    lazyInitialization = new test();
                }
            }


        }

        return lazyInitialization;
    }
}
