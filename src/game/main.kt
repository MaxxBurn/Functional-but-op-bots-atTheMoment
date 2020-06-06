package game

import kotlin.random.Random

fun WrongSkill(temp:String? ,player: Fighter):String?{
    var temp2 = temp
    while(temp2 != "3" && temp2 != "2" && temp2 != "1"){
        println("Please insert proper skill")
        temp2 = readLine()
    }
    return temp2
}
fun EnumSkills(temp:String?):Skills{
    if(temp == "1"){
        return Skills.SKILL1
    }
    else if(temp == "2"){
        return Skills.SKILL2
    }
    else{
        return Skills.SKILL3
    }
}
//Fighting main function of the project
fun rendering(player:Fighter,enemy:Bot){
    println("---------------------------------------------------------------------------")
    println("You are facing ${enemy.name}. Enemy has ${enemy.hp}")
    var sleepCooldown:Int = 0
    var charmCooldown: Int = 0
    while(player.hp > 0 && enemy.hp >0 ){
        println("Which Skill do you want to use(use numbers): ")
        println("${player.skills(player.name)}")
        var temp = readLine()
        temp = WrongSkill(temp,player)
        player.dmgDealt(EnumSkills(temp),enemy)
        if (enemy.sleep == false && enemy.charm == false) {
            enemy.dmgDealt(player)
        }
        else{
            if ( enemy.charm == true){
                charmCooldown++
            }
            else if (enemy.sleep == true){
                sleepCooldown++
            }
            if(charmCooldown == 2){
                enemy.charm = false
            }
            if(sleepCooldown == 2){
                enemy.sleep = false
            }
        }
    }
}
fun main(){
    val player = pickYourFighter()
    pickItem(player)
    val enemy = enemyFighter()
    rendering(player,enemy)
}
fun pickYourFighter() : Fighter{

    println("Welcome to the fighter game. Please pick the name of your character")
    var name= readLine()
    var random:Fighter
    var right: Boolean = false
    while(!right){
        if(name != "Haste" && name!= "Tenebria" && name!= "Charles" && name!="Kuro" && name!="Chloe"){
            println("Please select a proper character:")
            name = readLine()
        }
        else if (name == "Kuro" || name == "Tenebria"|| name=="Charles"||name=="Chloe"||name=="Haste"){
            right = true
        }
    }
    random = when(name){
        "Haste"->Haste()
        "Tenebria"->Tenebria()
        "Charles"->Charles()
        "Kuro"->Kuro()
        "Chloe"->Chloe()
        else->Haste()
    }
    val player = random
    return player
}

fun pickItem(fighter:Fighter){
    println("Please pick your item between: ")
    println("1.Sword")
    println("2.Armor")
    println("3.Necklace")
    var name = readLine()
    while(name != "Sword" && name!= "Armor" && name != "Necklace" ){
        println("Please select a valid item")
        name = readLine()
    }
    println("Good Luck!!")
    fighter.item = name
}
//              NPC STATS
fun enemyFighter():Bot{
    val items = listOf<String>("Necklace","Armor","Sword")
    var atk:Int = Random.nextInt(100,201)
    var hp:Int = Random.nextInt(3000,6001)
    var def:Int = Random.nextInt(100,201)
    val randomItem:String? = items[Random.nextInt(0,3)]
    val enemy = Bot(hp,atk,def,randomItem)
    return enemy
}
