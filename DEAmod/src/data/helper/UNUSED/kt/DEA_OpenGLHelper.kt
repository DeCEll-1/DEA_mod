package data.helper.UNUSED.kt

import com.fs.starfarer.api.combat.ViewportAPI
import org.lwjgl.opengl.GL11
import java.awt.Color

class DEA_OpenGLHelper {

    public fun DEA_DrawLineKT(
        viewport: ViewportAPI?,
        lineFromX: Float,
        lineFromY: Float,
        lineToX: Float,
        lineToY: Float,
        color: Color
    ) {
        if (viewport != null) {//tomato says that i dont need kt and i trust him so this is unused (also doesnt work)

            GL11.glPushMatrix()

            GL11.glColor3f(1f, 1f, 1f)
            GL11.glBegin(GL11.GL_LINE) //those are unneeded, ruddy says. from ruddy:
//            https://discord.com/channels/187635036525166592/824910699415207937/1121785329272242267
//            >but to elaborate on it, begin & end are very old and not very efficient methods, there are more modern alternatives that run better

            GL11.glLineWidth(30f)

            GL11.glVertex2f(viewport.convertWorldXtoScreenX(lineFromX), viewport.convertWorldYtoScreenY(lineFromY))
            GL11.glVertex2f(viewport.convertWorldXtoScreenX(lineToX), viewport.convertWorldYtoScreenY(lineToY))
            GL11.glEnd()
//            GL11.glFlush()//this is unneeded since the game already makes this

            GL11.glPopMatrix()

        }
    }

}