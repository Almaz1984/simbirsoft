package com.almaz.task1.classes_block.subscriber_task;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class SubscribersList {
    public static void fillSubscribersList(ArrayList<Subscriber> subscribersList) {
        subscribersList.add(new Subscriber(0, "Ivanov", "Ivan", "Ivanovich", "Lenina 10", 100001, 10000, 5000, 0, 40));
        subscribersList.add(new Subscriber(1, "Bogdanov", "Renat", "Maratovich", "Zorge 30", 100002, 20000, 5000, 50, 0));
        subscribersList.add(new Subscriber(2, "Butin", "Vladimir", "Vladimirivich", "Bessonova 27", 100003, 5000, 5000, 20, 20));
        subscribersList.add(new Subscriber(3, "Kotov", "Alexander", "Nikolaevich", "Prospekt oktyabrya 100", 100004, 40000, 5000, 50, 50));
        subscribersList.add(new Subscriber(4, "Fadeev", "Konstantin", "Germanovich", "Lenina 33", 100005, 12000, 5000, 0, 0));
    }

    public static ArrayList<Subscriber> getSubscribersExceededLocalTimeTalks(int localTimeLimit, ArrayList<Subscriber> subscribersList) {
        return subscribersList.stream().
                filter(subscriber -> (subscriber.getLocalTalksTime() > localTimeLimit)).
                collect(Collectors.toCollection(ArrayList::new));
    }

    public static ArrayList<Subscriber> getSubscribersUsedLongDistanceTalks(ArrayList<Subscriber> subscribersList) {
        return subscribersList.stream().filter(subscriber -> (subscriber.getLongDistanceTalksTime() > 0)).
                collect(Collectors.toCollection(ArrayList::new));
    }

    public static void sortSubscribersAlphabetically(ArrayList<Subscriber> subscribersList) {
        subscribersList.sort(Comparator.comparing(Subscriber::getLastName));
    }
}
