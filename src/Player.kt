import kotlin.time.times

class Player constructor(var name: String, var hp: Int, var attack: Int, var mana: Int, var level: Int, var xp: Int, var potionInventory: MutableList<Item>) {
    fun Attack(monster: Monster,player: Player){
        if (player.hp >0 && monster.hp > 0){
            println("You attack the monster for " + player.attack * player.level+ " damage!")
            monster.hp -= player.attack * player.level
        }
    }
    fun MagicAttack(monster: Monster, player: Player){
        if(player.mana >= 10){
            println("You cast a firebolt! for "+ (player.attack * 2).times(player.level) + " damage!")
            monster.hp -= (player.attack * 2).times(player.level)
            player.mana -= 10
        }
        else{
            println("You don't have enough mana!")
        }
    }
    fun levelUp(player: Player){
        println("You've levelled up!")
        player.level += 1
        player.xp = 0
    }
    fun addItem(item: Item) {
        potionInventory.add(item)
    }
    fun openInventory(player: Player){
        val iterator = player.potionInventory.listIterator()
        while (iterator.hasNext()){
            val i = iterator.next()
        }
        while (iterator.hasPrevious()){
            val i = iterator.previousIndex()
        }
    }
}