class Monster(name: String, hp: Int,  attack: Int, xp: Int,var drop: Potion,icon : Char ) : Being(name,hp,attack,xp,icon) {
    //again, redundant in OOP
    fun monsterAttack(player: Player){
        if (hp > 0){
            println("The monster attacks you!")
            player.hp =- attack
        }
    }

    fun monsterDrop(): Potion {
        val rng = (0..3).random()
        return when (rng) {
            0 -> Potion("Healing potion", "Heals the player for 10 HP", 1, 10, 0)
            1 -> Potion("Mana potion", "Restores 10 mana", 2, 0, 10)
            2 -> Potion("Healing potion", "Heals the player for 10 HP", 1, 10, 0)
            3 -> Potion("Mana potion", "Restores 10 mana", 2, 0, 10)
            else -> throw IllegalStateException("Invalid drop")
            // if you plan on using this, you need to handle the exception or make it try/catch
        }
    }
}