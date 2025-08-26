package org.example.behavioral.chainresponsibility;

interface Handler {
    Handler setNext(Handler handler);

    void handle(String request);
}

// BaseHandler: centralizes the "chain link" (next reference + delegation).
abstract class BaseHandler implements Handler {
    private Handler nextHandler;

    @Override
    public Handler setNext(Handler handler) {
        this.nextHandler = handler;
        return handler;
    }

    @Override
    public void handle(String request) {
        if (this.nextHandler != null) {
            this.nextHandler.handle(request);
        }
    }
}

// Concrete Handler — decides to handle or delegate
class BasicSupport extends BaseHandler {
    @Override
    public void handle(String request) {
        if ("basic".equals(request)) {
            System.out.println("Basic Support: Solving basic issue");
            return;
        }
        System.out.println("Basic Support: Passing the issue to advanced support");
        super.handle(request);
    }
}

// Concrete Handler — decides to handle or delegate
class AdvancedSupport extends BaseHandler {
    @Override
    public void handle(String request) {
        if ("advanced".equals(request)) {
            System.out.println("Advanced Support: Solving advanced issue");
            return;
        }
        System.out.println("Advanced Support: Passing the issue to expert support");
        super.handle(request);
    }
}

// Concrete Handler — terminal node (does not call super.handle)
class ExpertSupport extends BaseHandler {
    @Override
    public void handle(String request) {
        if ("expert".equals(request)) {
            System.out.println("Expert Support: Solving expert issue");
            return;
        }
        System.out.println("Expert Support: Nothing else to do... bye bye");
    }
}

public class ChainResponsibilityDemo {
    public static void main(String[] args) {
        Handler basicSupport = new BasicSupport();
        Handler advancedSupport = new AdvancedSupport();
        Handler expertSupport = new ExpertSupport();

        // Defines the chain order; the "head" defines where we start
        basicSupport.setNext(advancedSupport).setNext(expertSupport);

        basicSupport.handle("basic");
        //basicSupport.handle("advanced");
        //basicSupport.handle("expert");
        //basicSupport.handle("nuclear");
    }
}