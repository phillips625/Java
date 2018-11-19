//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

                                  // INHERITANCE or INTERFACE
// - Key is to consider the class you're extending or implementing.
// For example ITelephone could have been extended to create a deskphone or a telephone - and it would probably not be a good idea.
// The way to decide which one to use is to consider the relationship between the final class and the class you're extending from.
// Since a mobile phone does more than make a phone call, it's more accurate to say that a mobile phone is a computer that has a phone
// aka ITelephone interface.
// Both Mobile Phone and the Deskphone can be used to make calls - which means they should both have a ITelephone interface.
// In Java, you can onoly INHEHRIT FROM ONE CLASS BUT YOU CAN INHERIT FROM MULTIPLE INTERFACES. MULTIPLE INHERITANCE IS ONLY AVAILABLE
// BY USING MULTIPLE INTERFACES.

// - A DOG WOULD EXTEND ANIMAL (a dog IS AN animal) AND IMPLEMENT WALK INTERFACE(multiple animals can walk).
// A BIRD would extend animal and implement WALK INTERFACE and FLY INTERFACE.
// -- Both dog and bird are animals but dogs can only walk whilst birds can walk and fly. --



// It's normal to declare a variable with a basic 'list', but then specify (whether it is an arraylist or a linkedlist or a vector)
// when it comes to initializing the list.

package com.Philco;

//import com.Philco.Album;
//import com.Philco.Song;

import java.util.*;

// Changing the linkedlists to basic 'lists' AND 'arraylists' with no qualms.
// This makes LISTS, ARRAYLISTS AND LINKEDLISTS INTERCHANGEABLE.
// BOTH ARRAYLISTS AND LINKEDLISTS IMPLEMENTS LIST! AS LONG AS YOU ARE ABLE TO IMPLEMENT ALL THE METHODS IN THE LIST INTERFACE, YOU CAN
// CREATE A CLASS THAT TAKES ADVANTAGE OF LIST'S INTERFACE FUNCTIONALITIES.


public class Main {
    private static List<Album> albums = new ArrayList();

    public Main() {
    }

    public static void main(String[] args) {
        Album album = new Album("Stormbringer", "Deep Purple");
        album.addSong("Stormbringer", 4.6D);
        album.addSong("Love don't mean a thing", 4.22D);
        album.addSong("Holy man", 4.3D);
        album.addSong("Hold on", 5.6D);
        album.addSong("Lady double dealer", 3.21D);
        album.addSong("You can't do it right", 6.23D);
        album.addSong("High ball shooter", 4.27D);
        album.addSong("The gypsy", 4.2D);
        album.addSong("Soldier of fortune", 3.13D);
        albums.add(album);
        album = new Album("For those about to rock", "AC/DC");
        album.addSong("For those about to rock", 5.44D);
        album.addSong("I put the finger on you", 3.25D);
        album.addSong("Lets go", 3.45D);
        album.addSong("Inject the venom", 3.33D);
        album.addSong("Snowballed", 4.51D);
        album.addSong("Evil walks", 3.45D);
        album.addSong("C.O.D.", 5.25D);
        album.addSong("Breaking the rules", 5.32D);
        album.addSong("Night of the long knives", 5.12D);
        albums.add(album);

        // This still works becauses the Java library uses INTERFACES EXTENSIVELY.

        // It's normal to declare a variable with a basic 'list', but then specify (whether it is an arraylist or a linkedlist or a vector)
// when it comes to initializing the list.
        //List<Song> playList = new ArrayList();
        // VECTOR IS ALSO ANOTHER TYPE OF LIST.
        List<Song> playList = new Vector<>();

        ((Album)albums.get(0)).addToPlayList("You can't do it right", playList);
        ((Album)albums.get(0)).addToPlayList("Holy man", playList);
        ((Album)albums.get(0)).addToPlayList("Speed king", playList);
        ((Album)albums.get(0)).addToPlayList(9, playList);
        ((Album)albums.get(1)).addToPlayList(8, playList);
        ((Album)albums.get(1)).addToPlayList(3, playList);
        ((Album)albums.get(1)).addToPlayList(2, playList);
        ((Album)albums.get(1)).addToPlayList(24, playList);
        play(playList);
    }

    private static void play(List<Song> playList) {
        Scanner scanner = new Scanner(System.in);
        boolean quit = false;
        boolean forward = true;
        ListIterator<Song> listIterator = playList.listIterator();
        if(playList.size() == 0) {
            System.out.println("No songs in playlist");
        } else {
            System.out.println("Now playing " + ((Song)listIterator.next()).toString());
            printMenu();

            while(!quit) {
                int action = scanner.nextInt();
                scanner.nextLine();
                switch(action) {
                    case 0:
                        System.out.println("Playlist complete.");
                        quit = true;
                        break;
                    case 1:
                        if(!forward) {
                            if(listIterator.hasNext()) {
                                listIterator.next();
                            }

                            forward = true;
                        }

                        if(listIterator.hasNext()) {
                            System.out.println("Now playing " + ((Song)listIterator.next()).toString());
                        } else {
                            System.out.println("We have reached the end of the playlist");
                            forward = false;
                        }
                        break;
                    case 2:
                        if(forward) {
                            if(listIterator.hasPrevious()) {
                                listIterator.previous();
                            }

                            forward = false;
                        }

                        if(listIterator.hasPrevious()) {
                            System.out.println("Now playing " + ((Song)listIterator.previous()).toString());
                        } else {
                            System.out.println("We are at the start of the playlist");
                            forward = true;
                        }
                        break;
                    case 3:
                        if(forward) {
                            if(listIterator.hasPrevious()) {
                                System.out.println("Now replaying " + ((Song)listIterator.previous()).toString());
                                forward = false;
                            } else {
                                System.out.println("We are at the start of the list");
                            }
                        } else if(listIterator.hasNext()) {
                            System.out.println("Now replaying " + ((Song)listIterator.next()).toString());
                            forward = true;
                        } else {
                            System.out.println("We have reached the end of the list");
                        }
                        break;
                    case 4:
                        printList(playList);
                        break;
                    case 5:
                        printMenu();
                        break;
                    case 6:
                        if(playList.size() > 0) {
                            listIterator.remove();
                            if(listIterator.hasNext()) {
                                System.out.println("Now playing " + listIterator.next());
                            } else if(listIterator.hasPrevious()) {
                                System.out.println("Now playing " + listIterator.previous());
                            }
                        }
                }
            }

        }
    }

    private static void printMenu() {
        System.out.println("Available actions:\npress");
        System.out.println("0 - to quit\n1 - to play next song\n2 - to play previous song\n3 - to replay the current song\n4 - list songs in the playlist\n5 - print available actions.\n6 - delete current song from playlist");
    }

    private static void printList(List<Song> playList) {
        Iterator<Song> iterator = playList.iterator();
        System.out.println("================================");

        while(iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        System.out.println("================================");
    }
}
