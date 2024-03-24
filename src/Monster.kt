class Monster constructor(var name: String, var hp: Int, var attack: Int, var xp: Int, var drop: Item) {
    fun monsterAttack(monster: Monster, player: Player){
        if (monster.hp > 0){
            println("The monster attacks you!")
            player.hp =- monster.attack
        }
    }
}