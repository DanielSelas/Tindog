package com.danielsela.myapplication.Utiles;

import static com.danielsela.myapplication.Utiles.Const.CHAT_NODE;
import static com.danielsela.myapplication.Utiles.Const.DOGS_NODE;
import static com.danielsela.myapplication.Utiles.Const.PERSONS_NODE;
import static com.danielsela.myapplication.Utiles.Const.USER_NODE;

import android.util.Log;

import com.danielsela.myapplication.Interfaces.DogCallback;
import com.danielsela.myapplication.Interfaces.PersonCallback;
import com.danielsela.myapplication.Models.Chat;
import com.danielsela.myapplication.Models.Dog;
import com.danielsela.myapplication.Models.Person;
import com.danielsela.myapplication.Models.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class DataManager {

    private static DataManager instance;
    private PersonCallback personCallback;
    private DogCallback dogCallback;

    private DataManager() {
    }

    public static DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
        }
        return instance;
    }

    public void setPersonCallback(PersonCallback callback) {
        this.personCallback = callback;
    }

    public void setDogCallback(DogCallback callback) {
        this.dogCallback = callback;
    }


    public static User getUser() {
        User user = new User();
        user.setName("Daniel")
                .setDogName("Billy")
                .setDogBreed("Pitbull")
                .setEnergyLevel(5.0f)
                .setPlayStyle("Aggresive")
                .setMail("danielsela96@gmail.com")
                .setProfilePicutre("https://scontent.ftlv20-2.fna.fbcdn.net/v/t1.6435-9/125307252_10223626592042760_3370004293084215372_n.jpg?_nc_cat=101&ccb=1-7&_nc_sid=5f2048&_nc_ohc=oKEEtpCdNYUAb5QHrM9&_nc_oc=AdjGEVi8O1vlghBXrgj2qXNt2hPvaUmCSjfdBfYbKHFYNVsCS50hCyO6SAzSUdL7n3Y6qZjZH_3wU6wb-VxfrN2M&_nc_ht=scontent.ftlv20-2.fna&oh=00_AfBJFnie8_IAEKIrofk8qUCELTQFnCw8Nvxynqh-r0G-uQ&oe=664B12ED");
        return user;
    }
    public static void uploadUserToFirebase(User user) {
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference(USER_NODE);
        userRef.child(USER_NODE).setValue(user);
    }

    public static ArrayList<Person> getPerson(){
        ArrayList<Person> person = new ArrayList<>();

        person.add(new Person()
                .setName("Daniel")
                .setLocation("Tel-Aviv")
                .setDaysOfAvailability(5)
                .setPrice(50)
                .setPersonPoster("https://media.istockphoto.com/id/1179213726/photo/man-and-dog.jpg?s=612x612&w=0&k=20&c=2eNc47hxjSFuJkc6ywawqlUk2jIlezio0OYezAZ1lMY=")
        );

        person.add(new Person()
                .setName("Ben")
                .setLocation("Herzelya")
                .setDaysOfAvailability(3)
                .setPrice(80)
                .setPersonPoster("https://media.istockphoto.com/id/1300658241/photo/young-man-is-playing-with-a-dog-and-do-selfie.jpg?s=612x612&w=0&k=20&c=3GuywLL9CeC7VRRcbH35ZRYLRtvmObrvmFjQqTgNjCE=")
        );


        person.add(new Person()
                .setName("Neta")
                .setLocation("Haifa")
                .setDaysOfAvailability(7)
                .setPrice(60)
                .setPersonPoster("https://media.istockphoto.com/id/1060529042/photo/young-woman-with-dog.jpg?s=2048x2048&w=is&k=20&c=u-XGXmIDklRxflv2pIQQK3QwxNHaO4GDz7oVJ09dcuI=")
        );

        person.add(new Person()
                .setName("Nitzan")
                .setLocation("Ramat-Ishai")
                .setDaysOfAvailability(2)
                .setPrice(55)
                .setPersonPoster("https://media.istockphoto.com/id/1179643742/photo/morning-with-my-pet-in-our-kitchen.jpg?s=2048x2048&w=is&k=20&c=mFyjzTT_kmuOURCkoEkoVnBXfJ5p9j4ERhZBl3jFfjM=")
        );

        return person;
    }
    public static void uploadPersonsToFirebase(ArrayList<Person> personList) {
        DatabaseReference personsRef = FirebaseDatabase.getInstance().getReference().child(PERSONS_NODE);
        for (Person person : personList) {
            personsRef.push().setValue(person);
        }
    }

    public static ArrayList<Dog> getDogs(){
        ArrayList<Dog> dogs = new ArrayList<>();

        dogs.add(new Dog()
                .setName("Billy")
                .setLocation("Tel-Aviv")
                .setPlayStyle("Aggressive")
                .setBread("Pitbull")
                .setEnergy(5.0f)
                .setDogPoster("https://cdn.shopify.com/s/files/1/1638/5471/files/Picture1_8287ba9b-7b2f-4f15-b6d0-1b9192cf9764_480x480.jpg?v%3D1659447491&imgrefurl=https://www.sparkpaws.es/blogs/community/how-strong-is-the-bite-force-of-a-pitbull&h=480&w=480&tbnid=Vmh1HbJkUE_AXM&source=sa.im&tbnh=225&tbnw=225&usg=AI4_-kTtX_lvXBxydc6ikKCdigLptC9kNw&vet=1&docid=bb-C5kTOYnjI7M")

        );

        dogs.add(new Dog()
                .setName("Garry")
                .setLocation("Herzylia")
                .setPlayStyle("Balanced")
                .setBread("Shapperd")
                .setEnergy(3.5f)
                .setDogPoster("https://www.pdsa.org.uk/media/6620/german-shepherd-gallery-6-min.jpg?anchor%3Dcenter%26mode%3Dcrop%26quality%3D100%26height%3D500%26bgcolor%3Dfff%26rnd%3D132019595570000000&imgrefurl=https://www.pdsa.org.uk/pet-help-and-advice/looking-after-your-pet/puppies-dogs/large-dogs/german-shepherd&h=500&w=500&tbnid=qtNu2MUW9mH2fM&source=sa.im&tbnh=225&tbnw=225&usg=AI4_-kRZqRfY5KoFcCIneiXulv0akUdt5g&vet=1&docid=yKLR2MMm8rQp6M")

        );


        dogs.add(new Dog()
                .setName("Tuti")
                .setLocation("Haifa")
                .setPlayStyle("light")
                .setBread("Shitzu")
                .setEnergy(1.0f)
                .setDogPoster("https://media.istockphoto.com/id/1190210049/photo/cute-shitzu-puppy-in-the-park.jpg?s=2048x2048&w=is&k=20&c=0mRE7fl7urAQAwqm9dKCQZBfLywvqfHUYRuvC7bP_jo=")

        );

        dogs.add(new Dog()
                .setName("Goku")
                .setLocation("Ramat-Ishai")
                .setPlayStyle("Aggressive")
                .setBread("Vissla")
                .setEnergy(4.5f)
                .setDogPoster("https://media.istockphoto.com/id/657543134/photo/running-hungarian-vizsla.jpg?s=2048x2048&w=is&k=20&c=x-Ohu_SMmKasQ2lYfHKIzR-T_8-Zuyr5kY7e8CmDfXo=")

        );

        return dogs;
    }
    public static void uploadDogsToFirebase(ArrayList<Dog> dogList) {
        DatabaseReference dogsRef = FirebaseDatabase.getInstance().getReference().child(DOGS_NODE);
        for (Dog dog : dogList) {
            dogsRef.push().setValue(dog);
        }
    }

    public static ArrayList<Chat> getChats(){
        ArrayList<Chat> chatList = new ArrayList<>();
        chatList.add(new Chat()
                .setName("Michal")
                .setPicture("https://media.istockphoto.com/id/467923438/photo/silly-dog-tilts-head-in-front-of-barn.jpg?s=2048x2048&w=is&k=20&c=H_cR-9-qXOb3jUZWnsWwwzhdpSMnA90C9Fa7qb8dPjs=")
                .setMessages(Collections.singletonList("Saw your dog, i think it a match!"))
        );
        chatList.add(new Chat()
                .setName("Eyal")
                .setPicture("https://media.istockphoto.com/id/1041987488/photo/cute-dog-put-his-face-on-his-knees-to-the-man-and-smiling-from-the-hands-scratching-her-ear.jpg?s=612x612&w=0&k=20&c=NKGf8nmXVdksmNS0Ay696cVPNSIfCJJ1yu_y9jFGBsM=")
                .setMessages(Collections.singletonList("Love to meet your dog!"))
        );
        chatList.add(new Chat()
                .setName("Yuval")
                .setPicture("https://media.istockphoto.com/id/175453491/photo/dalmatian.jpg?s=612x612&w=0&k=20&c=eEiw7kpuHxnUogCEE0b-TKrWCXjPJmnip_xE1bHoi_Y=")
                .setMessages(Collections.singletonList("so cute!!!"))
        );

        return chatList;
    }
    public static void uploadChatsToFirebase(ArrayList<Chat> chatList) {
        DatabaseReference chatRef = FirebaseDatabase.getInstance().getReference().child(CHAT_NODE);
        for (Chat chat : chatList) {
            chatRef.push().setValue(chat);
        }
    }

}