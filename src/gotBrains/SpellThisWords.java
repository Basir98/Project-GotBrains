package gotBrains;

import java.util.LinkedList;

public class SpellThisWords {
	String[] wordsEasy = {"Sun", "Son", "Life", "Love", "Fond", "Ring", "Bag", "Cold", "Fish", "Hell", "Five", "Wolf",
			"Star", "King", "Time", "Tree", "City", "Sing", "Lion", "Foot", "Cool", "Body", "Golf", "Moon", "Work",
			"Lady", "Cake", "Blue", "High", "Rock", "Face", "Good", "Hate", "Pink", "Oven", "Bear", "Snow", "Taco",
			"Zero", "Town", "Book", "Card", "Bomb", "Game", "Year", "Worm", "Rage", "Quit", "Ugly", "Rice", "World",
			"Pizza", "Water", "Month", "Angel", "Death", "Music", "Sugar", "Woman", "Party", "Peace", "Tiger", "Earth",
			"House", "Lemon", "Watch", "Clock", "Lock", "Stone", "Santa", "China", "Jesus", "South", "North", "East",
			"West", "Blood", "Light", "India", "Power", "Anger", "Night", "April", "Puppy", "Phone", "Queen", "Pasta",
			"Smart", "Knife", "Magic", "Black", "Media", "Truth", "Zebra", "Mango", "Dirty", "Fruit", "Panda", "Radio",
			"Dance" };

	String[] wordsMedium = { "Hello", "Purple", "Twelve", "Samsung", "Heaven", "Banana", "Africa", "Office", "Snitch",
			"Pumpkin", "Perfect", "Freedom", "Nothing", "History", "Amazing", "Welcome", "Secret", "Dolphin", "Justice",
			"Animal", "Mother", "Father", "Pirate", "Winter", "Summer", "Friend", "Memory", "Bottle", "Couple",
			"Simple", "Guitar", "Police", "Bullet", "Soccer", "Hungry", "Murder", "Travel", "Killer", "Finger",
			"Second", "First", "Shadow", "Peanut", "Zombie", "Puzzle", "Vision", "Target", "Option", "Cheese", "Rocket",
			"Artist", "Riddle", "Subway", "Player", "Temple", "Public", "Europe", "Cactus", "Prison", "Square",
			"Galaxy", "Empire", "Genius", "Helium", "Random", "Object", "Motion", "Sports", "Rhythm", "Movies",
			"Cowboy", "Legacy", "Trophy", "Social", "Planet", "Needle", "Boxing", "Pocket", "Jacket", "Strange",
			"Muscle", "Effect", "Accent", "Orphan", "Karate", "Wasted", "Marine", "Advice", "Charge", "Jaguar",
			"Reward", "Biscuit", "Tragedy", "Contact", "Trigger", "Lyrical", "Gallery", "Inspire", "Society",
			"Sweater" };

	String[] wordsHard = { "Valkyire", "Football", "Strength", "February", "Building", "Relationship", "Calendar",
			"November", "Everything", "Basketball", "Technology", "Watermelon", "Champion", "Hospital", "Television",
			"Friendship", "Medicine", "University", "Blackboard", "Whiteboard", "Jupiter", "Mountain", "Umbrella",
			"Computer", "Electric", "Doughnut", "Paradise", "Keyboard", "Dinosaur", "Aquarium", "Aardvark", "Predator",
			"Smoothie", "Military", "Festival", "Campfire", "Training", "Lemonade", "Popsicle", "Broccoli", "Fraction",
			"Addition", "Anaconda", "Blackout", "Audience", "Creative", "Division", "Goldfish", "Colorful", "Geometry",
			"Nitrogen", "Fabulous", "Dumpster", "Mattress", "Blizzard", "Eighteen", "Elevator", "Discover", "Register",
			"Gasoline", "Exercise", "Question", "Bracelet", "Complain", "Antelope", "Wardrobe", "Ambiance", "Boneless",
			"Aerobics", "Pentagon", "Backpack", "Cylinder", "Location", "Juvenile", "Universe", "Prospect", "Whiskers",
			"Industry", "Wellness", "Conflict", "Producer", "Handsome", "Dynamite", "Mosquito", "Fortress", "District",
			"Teamwork", "Bathroom", "Humanity", "Donation", "Moonlight", "Aeroplane", "Particles", "Invention",
			"Milkshake", "Evolution", "Exception", "Autograph", "Champagne", "Authentic" };

	LinkedList<String> easyWords = new LinkedList<String>();
	LinkedList<String> mediumWords = new LinkedList<String>();
	LinkedList<String> hardWords = new LinkedList<String>();
	
	public SpellThisWords() {
		populateLists();
	}
	
	public void populateLists() {
		for (int i = 0; i < wordsEasy.length; i++) {
			easyWords.add(wordsEasy[i]);
		}
		for (int i = 0; i < wordsMedium.length; i++) {
			mediumWords.add(wordsMedium[i]);
		}
		for (int i = 0; i < wordsMedium.length; i++) {
			hardWords.add(wordsHard[i]);
		}
	}

	public LinkedList<String> getEasyWords() {
		return easyWords;
	}

	public LinkedList<String> getMediumWords() {
		return mediumWords;
	}

	public LinkedList<String> getHardWords() {
		return hardWords;
	}

}
