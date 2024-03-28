
class Potion(name: String,desc: String, id: Int, var healing: Int, var manaHealing: Int) : Item(name, desc, id) {
    fun useHealingPotion(potion: Potion, player: Player){
        println("You use the healing potion, restoring ${potion.healing} HP!")
        player.hp += potion.healing
    }
    fun useManaPotion(potion: Potion, player: Player){
        println("You use the mana potion and restore ${potion.manaHealing} MP!")
    }

}