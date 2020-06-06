package game

import javax.swing.text.StyledEditorKit
import kotlin.random.Random

enum class Skills{
    SKILL1,
    SKILL2,
    SKILL3
}
abstract class Fighter(): SkillName, StatusEffects(){
    abstract var hp: Int
    abstract var atk:Int
    abstract var def: Int
    abstract var item:String?
    abstract val name:String
    abstract fun dmgDealt(ability: Skills, enemy:Bot)
}

class Haste(): Fighter(){
    init {
        println("Your character is Haste")
    }
    override val name = "Haste"
    override var hp: Int = 2000
    override var atk: Int = 250
    override var def: Int = 200
    override var item: String? = null
    var s2Cooldown: Int = 0
    var s3Cooldown: Int = 0
    override fun dmgDealt(ability: Skills,enemy: Bot) {
        if (ability == Skills.SKILL1) {
            println("Haste uses Scythe")
            val skillMult: Double = 0.34
            when(enemy.vamp){
                true->{enemy.hp -= (skillMult * this.atk + this.atk).toInt() - (0.6 * enemy.def).toInt()
                    println("Haste regened 800 hp")
                    this.hp += 800}
                false->enemy.hp -= (skillMult * this.atk + this.atk).toInt() - (0.6 * enemy.def).toInt()
            }
        }
        else if (ability == Skills.SKILL2) {
            println("Haste uses Blood Rend")
            val skillMult: Double = 0.56
            val temp = Random.nextInt(0,11)
            if(temp > 5){
                enemy.vamp = true
            }
            enemy.hp -= (skillMult * this.atk + this.atk).toInt()
            this.s2Cooldown = 2
        }
        else {
            println("Haste uses Vampiric Scepter")
            val skillMult: Double = 0.6
            this.hp += 4 * this.atk
            this.s3Cooldown = 3
        }
    }
}
class Tenebria():Fighter(){

    init {
        println("Your character is Tenebria")
    }
    override val name = "Tenebria"
    override var hp: Int = 2000
    override var atk: Int = 250
    override var def: Int = 200
    override var item: String? = null
    var s2Cooldown: Int = 0
    var s3Cooldown: Int = 0
    override fun dmgDealt(ability: Skills,enemy: Bot) {
        if (ability == Skills.SKILL1) {
            println("Tenebria uses Spikes of Hell")
            val skillMult: Double = 0.34
            if(enemy.sleep == true){
                enemy.hp -= (skillMult * this.atk + this.atk +(this.atk* 1.5)).toInt() - (0.6 * enemy.def).toInt()
            }
            else if(enemy.charm == true){
                enemy.hp -= (skillMult * this.atk + this.atk).toInt() - (0.6 * enemy.def).toInt()
                this.atk += 100
                println("Tenebria got a confidence boost! Her attack is increased by 100. Attack: ${this.atk}")
            }
            else {
                enemy.hp -= (skillMult * this.atk + this.atk).toInt() - (0.6 * enemy.def).toInt()
            }
        }
        else if (ability == Skills.SKILL2) {
            println("Tenebria uses Charm")
            val skillMult: Double = 0.56
            val temp: Int = Random.nextInt(0,11)
            if(temp > 7 ) {
                enemy.charm = true
                println("Enemy got charmed.")
            }
            else{
                println("Charm failed")
            }
            s2Cooldown = 4
        }
        else{
            println("Tenebria uses Endless Nightmare")
            val skillMult: Double = 0.6
            val temp: Int = Random.nextInt(0,11)
            if(temp > 8){
                enemy.sleep = true
                println("Enemy is sleeping. ZzZzZzZ")
            }
            else{
                println("Sleep failed")
            }
            enemy.hp -= (skillMult * this.atk + this.atk).toInt() - (0.6 * enemy.def).toInt()
            s3Cooldown = 5
        }
    }
}
class Charles():Fighter(){

    init {
        println("Your character is Charles")
    }
    override val name = "Charles"
    override var hp: Int = 2000
    override var atk: Int = 250
    override var def: Int = 200
    override var item: String? = null
    var s2Cooldown: Int = 0
    var s3Cooldown: Int = 0
    override fun dmgDealt(ability: Skills,enemy:Bot) {
        if (ability == Skills.SKILL1) {
            println("Charles uses CounterAttack")
            val skillMult: Double = 0.56
        } else if (ability == Skills.SKILL2) {
            println("Charles uses Hail Aither!!")
            val skillMult: Double = 0.6
            hp += 400
            s3Cooldown = 4
        }
    }
}
class Kuro():Fighter(){

    init {
        println("Your character is Kuro")
    }
    override val name = "Kuro"
    override var hp: Int = 2000
    override var atk: Int = 120
    override var def: Int = 200
    override var item: String? = null
    var s2Cooldown: Int = 0
    var s3Cooldown: Int = 0
    override fun dmgDealt(ability: Skills,enemy:Bot) {
        if(ability == Skills.SKILL1){
            println("Kuro uses Mana Zone")
            this.atk += 60
            println("Kuro's attack is ${this.atk}")
            println()
        }
        else if (ability == Skills.SKILL2) {
            println("Kuro uses Lightless Slash and decreases the opponents defense by 50%.")
            val skillMult: Double = 0.21
            enemy.hp -= (skillMult * this.atk + this.atk).toInt() - (0.6 * enemy.def).toInt()
            enemy.def /= 2
            println("Enemy has ${enemy.hp}")
            s2Cooldown = 1
        } else if (ability == Skills.SKILL3) {
            println("Kuro uses Dimension Slash")
            val skillMult: Double = 4.0
            enemy.hp -= (skillMult * this.atk + this.atk).toInt() - (0.6 * enemy.def).toInt()
            this.atk += 200
            println("Kuro is further consumed by darkness and has his attack increased by 200 and losing 250 hp")
            this.hp -= 250
            println("Enemy has ${enemy.hp}")
            s3Cooldown = 5
        }
    }
}
class Chloe():Fighter(){

    init {
        println("Your character is Chloe")
    }
    override val name = "Chloe"
    override var hp: Int = 2000
    override var atk: Int = 250
    override var def: Int = 200
    override var item: String? = null
    var s2Cooldown: Int = 0
    var s3Cooldown: Int = 0
    override fun dmgDealt(ability: Skills,enemy: Bot) {
        if(ability==Skills.SKILL1){
            if(enemy.nail == true){
                enemy.hp -= (1.87* this.atk + this.atk).toInt() - (0.6 * enemy.def).toInt()
            }
        }
        else if (ability == Skills.SKILL2) {
            println("Chloe uses The Next Queen!")
            this.atk += 400
            println("Chloe's attack is increased by 400")
            this.s2Cooldown = 5
        } else if (ability == Skills.SKILL3) {
            println("Chloe uses あか")
            val skillMult: Double = 0.6
            val temp = Random.nextInt(0,11)
            if(temp > 4){
                enemy.nail = true
                println("Enemy has the nail effect on him.")
            }
            enemy.hp -= (1.1* this.atk + this.atk).toInt()- (0.6 * enemy.def).toInt()
            this.s3Cooldown = 3
        }
    }
}
open class Bot(override var hp:Int,override var atk:Int ,override var def:Int,override var item:String?):Fighter(),EnemyNames{

    override val name = name()
    override fun dmgDealt(ability: Skills, enemy: Bot) {}
    fun dmgDealt(player:Fighter){
        val random = Random.nextInt(0,3)
        if(random == 1){
            println("${this.name} uses Basic Attack")
            player.hp -= (0.5 * this.atk + this.atk ).toInt() - (0.6 * player.def).toInt()
            println("${player.name} has ${player.hp}")
        }
        else if(random == 2){
            println("${this.name} uses Hell Flame")
            player.hp -= (1.9 * this.atk + this.atk).toInt() - (0.6 * player.def).toInt()
            println("${player.name} has ${player.hp}")
        }
        else{
            println("${this.name} uses Heaven fall")
            player.hp -= (3* this.atk + this.atk).toInt() - (0.6 * player.def).toInt()
            println("${player.name} has ${player.hp}")
        }
    }
}

interface EnemyNames{
    fun name():String{
        val a = listOf<String>("SSB","Lidica","Alexa","Furious","Baal","Ray","Angelica")
        return a[Random.nextInt(0,7)]
    }
    fun enemyAbility():String{
        return "asd"
    }
}
interface SkillName{
    fun skills(name: String) :List<String>{
        when(name){
            "Haste"->return listOf("1.Scythe","2.Blood Rend","3.Vampiric Scepter")
            "Tenebria"->return listOf("1.Spikes of Hell","2.Charm","3.Endless Nightmare")
            "Charles"->return listOf("1.Sniper Attack","2.Counter Attack (passive)","3. Hail Aither")
            "Chloe"->return listOf("1.Hammer Time","2.Next Queen","3.Stubborn!")
            "Kuro"->return listOf("1.Mana Zone","2.Lightless Slash","3.Dimension Slash")
            else->return listOf("no skills")
        }
    }
}