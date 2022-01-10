package com.epam.rd.autocode.observer.git;


import java.util.ArrayList;
import java.util.List;

public class GitRepoObservers {
    static List<WebHook> hooks = new ArrayList<>();


    public static Repository newRepository(){
        return new Repository() {
            @Override
            public void addWebHook(WebHook webHook) {
                hooks.add(webHook);
            }

            @Override
            public Commit commit(String branch, String author, String[] changes) {
                for (WebHook h : hooks) {
                    if(branch.equals(h.branch())){
                        h.onEvent(new Event(h.type(), branch, List.of(new Commit(author, changes))));
                    }
                }
                return new Commit(author, changes);
            }

            @Override
            public void merge(String sourceBranch, String targetBranch) {
                List<Event> list = new ArrayList<>();
                for (WebHook h : hooks) {
                    if(h.branch().equals(sourceBranch)){
                        list = h.caughtEvents();
                    }
                }
                List<Commit> listCom = new ArrayList<>();
                for(int i = 0; i<list.size(); i++){
                    listCom.add(list.get(i).commits().get(0));
                }
                for(WebHook h : hooks){
                    if(h.branch().equals(targetBranch) && h.type().equals(Event.Type.MERGE)){
                        h.onEvent(new Event(Event.Type.MERGE, h.branch(), listCom));
                    }
                }
            }
        };
    }

    public static WebHook mergeToBranchWebHook(String branchName){
        List<Event> events = new ArrayList<>();
        return new WebHook() {
            @Override
            public String branch() {
                return branchName;
            }

            @Override
            public Event.Type type() {
                return Event.Type.MERGE;
            }

            @Override
            public List<Event> caughtEvents() {
                return List.of(events.get(0));
            }

            @Override
            public void onEvent(Event event) {
                events.add(event);
            }
        };
    }

    public static WebHook commitToBranchWebHook(String branchName){
        List<Event> events = new ArrayList<>();
     return new WebHook() {
         @Override
         public String branch() {
             return branchName;
         }

         @Override
         public Event.Type type() {
             return Event.Type.COMMIT;
         }

         @Override
         public List<Event> caughtEvents() {
             return events;
         }

         @Override
         public void onEvent(Event event) {
             events.add(event);
         }
     };
    }

}
