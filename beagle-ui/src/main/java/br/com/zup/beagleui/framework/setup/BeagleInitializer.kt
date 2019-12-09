package br.com.zup.beagleui.framework.setup

import android.app.Application
import br.com.zup.beagleui.framework.form.ValidatorHandler
import br.com.zup.beagleui.framework.action.CustomActionHandler
import br.com.zup.beagleui.framework.navigation.BeagleDeepLinkHandler
import br.com.zup.beagleui.framework.networking.HttpClient
import br.com.zup.beagleui.framework.view.WidgetViewFactory
import br.com.zup.beagleui.framework.widget.core.NativeWidget

enum class Environment {
    DEBUG,
    PRODUCTION
}

class BeagleInitializer private constructor() {

    companion object {

        @JvmStatic
        fun setup(
            appName: String,
            application: Application,
            environment: Environment,
            baseUrl: String
        ): Companion {
            BeagleEnvironment.setup(appName, application, environment, baseUrl)
            return this
        }

        @JvmStatic
        fun <T: NativeWidget> registerWidget(
            clazz: Class<T>,
            factory: WidgetViewFactory<T>
        ): Companion {
            BeagleEnvironment.registerWidget(clazz, factory)
            return this
        }

        @JvmStatic
        fun registerHttpClient(httpClient: HttpClient): Companion {
            BeagleEnvironment.httpClient = httpClient
            return this
        }

        @JvmStatic
        fun registerDesignSystem(designSystem: DesignSystem) {
            BeagleEnvironment.designSystem = designSystem
        }

        @JvmStatic
        fun registerBeagleDeepLinkHandler(beagleDeepLinkHandler: BeagleDeepLinkHandler): Companion {
            BeagleEnvironment.beagleDeepLinkHandler = beagleDeepLinkHandler
            return this
        }

        @JvmStatic
        fun registerValidatorHandler(validatorHandler: ValidatorHandler): Companion {
            BeagleEnvironment.validatorHandler = validatorHandler
            return this
        }

        @JvmStatic
        fun registerCustomActionHandler(customActionHandler: CustomActionHandler): Companion {
            BeagleEnvironment.customActionHandler = customActionHandler
            return this
        }
    }
}
