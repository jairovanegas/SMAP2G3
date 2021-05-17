package sma.grupo3.Retailer.DistributedBehavior;

import sma.grupo3.Retailer.Agents.Controller.ControllerAgent;

import java.util.*;
import java.util.stream.Collectors;

public class Services {
    private static ControllerAgent agent;
    private static Localities thisLocality;
    private static final Map<String, List<String>> globalDirectory = new Hashtable<>();
    private static final Map<Localities, Map<String, List<String>>> directory = new Hashtable<Localities, Map<String, List<String>>>();

    public static Localities getThisLocality() {
        return thisLocality;
    }

    public static void setThisLocality(Localities thisLocality) {
        Services.thisLocality = thisLocality;
    }

    public static void bindToService(String id, String service) {
        List<String> gDirectory = globalDirectory.computeIfAbsent(service, s -> Collections.synchronizedList(new ArrayList<>()));
        gDirectory.add(id);
        List<String> localityDirectory = directory.computeIfAbsent(thisLocality, localities -> new Hashtable<>()).computeIfAbsent(service, s -> Collections.synchronizedList(new ArrayList<>()));
        localityDirectory.add(id);
        agent.notifyServiceUpdate(service, localityDirectory);
    }

    public static boolean unbindFromService(String id, String service) {
        if (globalDirectory.containsKey(service)) {
            globalDirectory.get(service).remove(id);
            if (directory.containsKey(thisLocality)) {
                if (directory.get(thisLocality).containsKey(service)) {
                    directory.get(thisLocality).get(service).remove(id);
                    agent.notifyServiceUpdate(service, directory.get(thisLocality).get(service));
                    return true;
                }
            }
        }
        return false;
    }

    public static List<String> getLocalityServiceProviders(Localities locality, String service) {
        return directory.computeIfAbsent(locality, localities -> new Hashtable<>()).computeIfAbsent(service, s -> new ArrayList<>());
    }

    public static void updateServiceFromLocality(Localities locality, String service, List<String> update) {
        directory.computeIfAbsent(locality, k -> new Hashtable<>());
        directory.get(locality).put(service, update);
        globalDirectory.computeIfAbsent(service, s -> Collections.synchronizedList(new ArrayList<>()));
        List<String> globalService = globalDirectory.get(service);
        globalService.addAll(update.stream().filter(s -> !globalService.contains(s)).collect(Collectors.toList()));
    }

    public static List<String> getGlobalServiceProviders(String service) {
        return globalDirectory.get(service);
    }

    public static void setAgent(ControllerAgent _agent) {
        agent = _agent;
    }

}