package br.com.zup.beagle.engine.renderer.layout

import android.content.Context
import br.com.zup.beagle.core.ServerDrivenComponent
import br.com.zup.beagle.engine.renderer.RootView
import br.com.zup.beagle.engine.renderer.ViewRendererFactory
import br.com.zup.beagle.view.BeagleFlexView
import br.com.zup.beagle.view.ViewFactory
import br.com.zup.beagle.widget.core.Flex
import br.com.zup.beagle.widget.core.FlexDirection
import br.com.zup.beagle.widget.ui.Button
import io.mockk.MockKAnnotations
import io.mockk.Runs
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.just
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

internal class DirectionalView(val children: List<ServerDrivenComponent>) : ServerDrivenComponent

internal class DirectionalViewRendererImpl(
    override val component: DirectionalView,
    flex: Flex,
    viewRendererFactory: ViewRendererFactory,
    viewFactory: ViewFactory
) : DirectionalViewRenderer<DirectionalView>(
    component.children,
    flex,
    viewRendererFactory,
    viewFactory
) {

    override fun getYogaFlexDirection(): FlexDirection {
        return FlexDirection.COLUMN
    }
}

class DirectionalViewRendererTest {

    private var viewRendererFactory: ViewRendererFactory = mockk()

    private var viewFactory: ViewFactory = mockk()

    private var directionalView: DirectionalView =
        mockk(relaxUnitFun = true, relaxed = true)

    private var flex: Flex = mockk()

    private val children = listOf(Button(""), Button(""))

    @MockK
    private lateinit var beagleFlexView: BeagleFlexView

    @InjectMockKs
    private lateinit var directionalViewRenderer: DirectionalViewRendererImpl

    @Before
    fun setUp() {
        every { directionalView.children } returns children
        MockKAnnotations.init(this)
        every {
            flex.copy(
                flexDirection = any()
            )
        } returns flex
        every { flex.flexDirection } returns FlexDirection.COLUMN
    }

    @Test
    fun build_should_add_flexDirection_as_COLUMN_and_addView_to_yogaLayout() {
        // Given
        val beagleFlexView = mockk<BeagleFlexView>()
        val context = mockk<Context>()
        val rootView = mockk<RootView>()
        val flexSlot = slot<Flex>()
        every { rootView.getContext() } returns context
        every { viewFactory.makeBeagleFlexView(any(), capture(flexSlot)) } returns beagleFlexView
        every { beagleFlexView.addServerDrivenComponent(any(), any()) } just Runs

        // When
        directionalViewRenderer.build(rootView)

        // Then
        assertEquals(FlexDirection.COLUMN, flexSlot.captured.flexDirection)
        verify(exactly = 2) { beagleFlexView.addServerDrivenComponent(any(), rootView) }
    }
}