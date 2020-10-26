package heroRepository;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HeroRepositoryTest {
    private HeroRepository heroRepository;
    private Hero hero;
    private Hero hero2;
    private Hero hero3;

    @Before
    public void setup() {
        this.heroRepository = new HeroRepository();
        this.hero = new Hero("Pesho", 5, new Item(50, 50, 50));
        this.hero2 = new Hero("Gosho", 2, new Item(40, 60, 90));
        this.hero3 = new Hero("Sasho", 10, new Item(10, 20, 150));
    }

    @Test
    public void constructorShouldInitialiseEmptyData() {
        assertTrue(heroRepository.getCount() == 0);
    }

    @Test
    public void addHeroShouldAddHeroToDataIfItIsNotPreviouslyContained() {
        this.heroRepository.add(this.hero);
        assertTrue(this.heroRepository.getCount() == 1);
    }

    @Test (expected = IllegalArgumentException.class)
    public void addHeroShouldThrowExeptionIfHeroDuplicated() {
        this.heroRepository.add(this.hero);
        this.heroRepository.add(this.hero);
    }

    @Test
    public void removeHeroShouldRemoveHeroFromDataIfHeroWithSuchNameExists() {
        this.heroRepository.add(this.hero);
        this.heroRepository.remove("Pesho");
        assertTrue(heroRepository.getCount() == 0);
    }

    @Test (expected = NullPointerException.class)
    public void removeHeroShouldThrowExceptionIfNoSuchHeroExists() {
        this.heroRepository.remove("Pesho");
    }

    @Test
    public void getHeroWithHighestStrengthShouldReturnCorrectHero() {
        this.heroRepository.add(this.hero);
        this.heroRepository.add(this.hero2);
        this.heroRepository.add(this.hero3);

        Hero actual = this.heroRepository.getHeroWithHighestStrength();

        Hero expected = this.hero;

        assertEquals(actual, expected);
    }

    @Test (expected = NullPointerException.class)
    public void getHeroWithHighestStrengthShouldThrowExceptionIfHeroItemsHaveNegativeParams() {
        this.heroRepository.add(new Hero("Pesho", 5, new Item(-1, -2, -3)));
        this.heroRepository.add(new Hero("Gosho", 6, new Item(-4, -5, -6)));
        this.heroRepository.add(new Hero("Sasho", 7, new Item(-7, -8, -9)));

        this.heroRepository.getHeroWithHighestStrength();
    }

    //TODO MORE IF NEEDED

    @Test (expected = NullPointerException.class)
    public void getHeroWithHighestStrengthShouldThrowExceptionIfNoSuchFound() {
        this.heroRepository.getHeroWithHighestStrength();
    }

    @Test
    public void getHeroWithHighestAgilityShouldReturnCorrectHero() {
        this.heroRepository.add(this.hero);
        this.heroRepository.add(this.hero2);
        this.heroRepository.add(this.hero3);

        Hero actual = this.heroRepository.getHeroWithHighestAgility();

        Hero expected = this.hero2;

        assertEquals(actual, expected);
    }

    @Test (expected = NullPointerException.class)
    public void getHeroWithHighestAgilityShouldThrowExceptionIfHeroItemsHaveNegativeParams() {
        this.heroRepository.add(new Hero("Pesho", 5, new Item(-1, -2, -3)));
        this.heroRepository.add(new Hero("Gosho", 6, new Item(-4, -5, -6)));
        this.heroRepository.add(new Hero("Sasho", 7, new Item(-7, -8, -9)));

        this.heroRepository.getHeroWithHighestAgility();
    }

    //TODO MORE IF NEEDED

    @Test (expected = NullPointerException.class)
    public void getHeroWithHighestAgilityShouldThrowExceptionIfNoSuchFound() {
        this.heroRepository.getHeroWithHighestAgility();
    }

    @Test
    public void getHeroWithHighestIntelligenceShouldReturnCorrectHero() {
        this.heroRepository.add(this.hero);
        this.heroRepository.add(this.hero2);
        this.heroRepository.add(this.hero3);

        Hero actual = this.heroRepository.getHeroWithHighestIntelligence();

        Hero expected = this.hero3;

        assertEquals(actual, expected);
    }

    @Test (expected = NullPointerException.class)
    public void getHeroWithHighestIntelligenceShouldThrowExceptionIfHeroItemsHaveNegativeParams() {
        this.heroRepository.add(new Hero("Pesho", 5, new Item(-1, -2, -3)));
        this.heroRepository.add(new Hero("Gosho", 6, new Item(-4, -5, -6)));
        this.heroRepository.add(new Hero("Sasho", 7, new Item(-7, -8, -9)));

        this.heroRepository.getHeroWithHighestIntelligence();
    }

    //TODO MORE IF NEEDED

    @Test (expected = NullPointerException.class)
    public void getHeroWithHighestIntelligenceShouldThrowExceptionIfNoSuchFound() {
        this.heroRepository.getHeroWithHighestIntelligence();
    }

    @Test
    public void getCountShouldReturnCorrectDataCountWhenRepositoryIsEmpty() {
        assertTrue(this.heroRepository.getCount() == 0);
    }

    @Test
    public void getCountShouldReturnCorrectDataCountWhenRepositoryHasSeveralHeroes() {
        this.heroRepository.add(hero3);
        this.heroRepository.add(hero2);

        assertTrue(this.heroRepository.getCount() == 2);
    }

    @Test
    public void toStringShouldReturnEmptyStringIfRepositoryIsEmpty() {
        String actual = this.heroRepository.toString();
        String expected = "";
        assertEquals(actual, expected);
    }

    @Test
    public void toStringShouldReturnCorrectResultWhenSeveralHeroes() {
        this.heroRepository.add(this.hero);
        this.heroRepository.add(this.hero2);
        this.heroRepository.add(this.hero3);

        String actual = this.heroRepository.toString();

        StringBuilder expected = new StringBuilder();
        expected.append(hero.toString())
                .append(hero2.toString())
                .append(hero3.toString());

        assertEquals(actual, expected.toString());
    }

}