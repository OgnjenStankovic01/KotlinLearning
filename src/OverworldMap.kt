class OverworldMap(val position: Position, val being: Being) {
    fun drawWorldMap(worldMap: HashMap<Position, Being>) {
        for (x in 0..5) {
            for (y in 0..5) {
                var currentPos = Position(x, y)
                if (worldMap.containsKey(currentPos)) {
                    var monster = worldMap[currentPos]
                    if (monster != null) {
                        print(" ${monster.icon} ")
                    }
                } else {
                    print(" X ")
                }

            }
            println()
        }
    }
}
