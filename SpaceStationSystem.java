import java.util.*;

final class SecurityClearance {
    private final String id, level; 
    private final String[] sections;
    private final long exp;

    public SecurityClearance(String id, String level, String[] sections, long exp) {
        this.id = id; this.level = level.toUpperCase();
        this.sections = sections.clone(); this.exp = exp;
    }
    public final boolean canAccess(String s) { return !isExpired() && Arrays.asList(sections).contains(s); }
    public final boolean isExpired() { return System.currentTimeMillis() > exp; }
    public final int getAccessHash() { return Objects.hash(id, level, Arrays.hashCode(sections), exp); }
    public String getId() { return id; }
    public String getLevel() { return level; }
    public String[] getSections() { return sections.clone(); }
    public long getExp() { return exp; }
}

final class CrewRank {
    private final String name; private final int level; private final String[] perms;
    private CrewRank(String n, int l, String[] p) { name=n; level=l; perms=p.clone(); }
    public static CrewRank cadet()     { return new CrewRank("Cadet",1,new String[]{"BASIC"}); }
    public static CrewRank officer()   { return new CrewRank("Officer",2,new String[]{"BASIC","MISSION"}); }
    public static CrewRank commander() { return new CrewRank("Commander",3,new String[]{"MISSION","SECURE"}); }
    public static CrewRank captain()   { return new CrewRank("Captain",4,new String[]{"SECURE","ADMIN"}); }
    public static CrewRank admiral()   { return new CrewRank("Admiral",5,new String[]{"ALL"}); }
    public String getName(){return name;} public int getLevel(){return level;}
    public String[] getPerms(){return perms.clone();}
}

class SpaceCrew {
    public static final String STATION="Stellar Odyssey"; 
    public static final int MAX=50;
    private final String id, planet; 
    private final SecurityClearance clearance; 
    private final CrewRank initRank;
    private CrewRank curRank; private int missions; private double hours;

    public SpaceCrew(String id){ this(id,"Randomia",CrewRank.cadet(),0,0,tmpClear()); }
    public SpaceCrew(String id,String p,CrewRank r){ this(id,p,r,0,0,tmpClear()); }
    public SpaceCrew(String id,String p,CrewRank r,int m,double h){ this(id,p,r,m,h,tmpClear()); }
    public SpaceCrew(String id,String p,CrewRank r,int m,double h,SecurityClearance c){
        this.id=id; planet=p; initRank=r; curRank=r; missions=m; hours=h; clearance=c;
    }
    private static SecurityClearance tmpClear(){ return new SecurityClearance("TMP", "LOW", new String[]{"QUARTERS"}, System.currentTimeMillis()+1_000_000L); }
    public final String getCrewIdentification(){ return id+":"+initRank.getName(); }
    public final boolean canBePromoted(){ return !clearance.isExpired() && missions>=initRank.getLevel()*2; }
    public final int calculateSecurityRating(){ return clearance.getAccessHash()+initRank.getLevel(); }
    public void addMission(){missions++;} public void addHours(double h){hours+=h;}
    public boolean promote(CrewRank r){ if(r.getLevel()>curRank.getLevel() && canBePromoted()){curRank=r; return true;} return false; }
}

final class CommandCrew { 
    private final SpaceCrew core; private final Set<String> certs; 
    public CommandCrew(SpaceCrew c,Set<String> s){core=c; certs=Set.copyOf(s);} 
}
final class PilotCrew { 
    private final SpaceCrew core; private final Set<String> certs; 
    public PilotCrew(SpaceCrew c,Set<String> s){core=c; certs=Set.copyOf(s);} 
}
final class ScienceCrew { 
    private final SpaceCrew core; private final String spec; 
    public ScienceCrew(SpaceCrew c,String s){core=c; spec=s;} 
}
final class EngineerCrew { 
    private final SpaceCrew core; private final String type; 
    public EngineerCrew(SpaceCrew c,String t){core=c; type=t;} 
}

final class SpaceStationRegistry {
    private static final Map<String,Object> reg=new HashMap<>();
    public static boolean register(Object o){
        SpaceCrew base = (o instanceof SpaceCrew sc)?sc:
                         (o instanceof CommandCrew cc)?cc.core:
                         (o instanceof PilotCrew pc)?pc.core:
                         (o instanceof ScienceCrew sc2)?sc2.core:
                         (o instanceof EngineerCrew ec)?ec.core:null;
        if(base==null||reg.size()>=SpaceCrew.MAX) return false;
        return reg.putIfAbsent(base.getCrewIdentification(),o)==null;
    }
    public static List<Object> getByType(Class<?> t){
        return reg.values().stream().filter(t::isInstance).toList();
    }
}

public class SpaceStationSystem {
    public static void main(String[] a){
        SecurityClearance c=new SecurityClearance("C1","HIGH",new String[]{"BRIDGE"},System.currentTimeMillis()+9999999);
        SpaceCrew alice=new SpaceCrew("A1","Earth",CrewRank.captain(),5,1000,c);
        CommandCrew cmd=new CommandCrew(alice,Set.of("FleetOps"));
        SpaceCrew bob=new SpaceCrew("B1");
        PilotCrew pilot=new PilotCrew(bob,Set.of("WarpPilot"));

        SpaceStationRegistry.register(alice);
        SpaceStationRegistry.register(cmd);
        SpaceStationRegistry.register(pilot);

        System.out.println(alice.getCrewIdentification());
        System.out.println("Promotable? "+alice.canBePromoted());
        System.out.println("Registry CommandCrews: "+SpaceStationRegistry.getByType(CommandCrew.class));
    }
}
