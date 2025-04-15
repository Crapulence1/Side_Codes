package textAdventure;

import textAdventure.Armors.Armor;
import textAdventure.Entities.Enemys.Enemy;
import textAdventure.Entities.Player;
import textAdventure.Locations.Coordinate;
import textAdventure.Spells.Fireball;
import textAdventure.Weapons.Weapon;

import java.util.ArrayList;

public class test {
    public static void main(String[] args) throws Exception {
        enemyFactory generator = new enemyFactory(1);
        Player player = new Player();
        player.setEquippedWeapon(new Weapon("Sword", 12, new Affinity("Slash", Type.slash)));
        player.setEquippedArmor(new Armor("Armor", 15, "Slash"));
        player.learnSpell(new Fireball());
        ArrayList<Enemy> enemies = generator.getEnemies(2, new Coordinate(0, 0));
        for(Enemy enemy: enemies){
            System.out.println(enemy.getName());
            enemy.printStats();
        }
        Battle battle = new Battle(player, enemies);
        battle.startBattle();
    }
}
