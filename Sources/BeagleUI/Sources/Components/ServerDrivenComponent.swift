//
//  Copyright © 2019 Zup IT. All rights reserved.
//

import UIKit

public protocol ServerDrivenComponent: Renderable {}

public protocol ComposeComponent: ServerDrivenComponent {
    func build() -> ServerDrivenComponent
}

extension ComposeComponent {
    public func toView(context: BeagleContext, dependencies: RenderableDependencies) -> UIView {
        return build().toView(context: context, dependencies: dependencies)
    }
}

public protocol Renderable {
    func toView(context: BeagleContext, dependencies: RenderableDependencies) -> UIView
}

public protocol RenderableDependencies:
    DependencyTheme,
    DependencyValidatorProvider,
    DependencyPreFetching,
    DependencyAppBundle,
    DependencyNetwork,
    DependencyCacheManager {
}

extension ServerDrivenComponent {
    public func toScreen() -> Screen {
        let screen = self as? ScreenComponent
        let safeArea = screen?.safeArea
            ?? SafeArea(top: true, leading: true, bottom: true, trailing: true)

        return Screen(
            appearance: screen?.appearance,
            safeArea: safeArea,
            navigationBar: screen?.navigationBar,
            header: screen?.header,
            child: screen?.child ?? self,
            footer: screen?.footer
        )
    }
}

// Defines a representation of an unknwon Component
public struct AnyComponent: ServerDrivenComponent {
    public let value: Any
    
    public init(value: Any) {
        self.value = value
    }
}

extension AnyComponent: Renderable {
    public func toView(context: BeagleContext, dependencies: RenderableDependencies) -> UIView {
        let label = UILabel(frame: .zero)
        label.numberOfLines = 2
        label.text = "Unknown Component of type:\n \(String(describing: value))"
        label.textColor = .red
        label.backgroundColor = .yellow
        return label
    }
}
