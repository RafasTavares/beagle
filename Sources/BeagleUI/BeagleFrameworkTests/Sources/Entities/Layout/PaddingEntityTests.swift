//
//  PaddingEntityTests.swift
//  BeagleFrameworkTests
//
//  Created by Eduardo Sanches Bocato on 19/09/19.
//  Copyright © 2019 Daniel Tes. All rights reserved.
//

import XCTest
@testable import BeagleUI

final class PaddingEntityTests: XCTestCase {
    
    func test_whenMapToWidgetIsCalled_thenItShouldReturnAPaddingWidget() {
        // Given
        let content = TextEntity(text: "text")
        let value = PaddingValueEntity(top: nil, left: nil, right: nil, bottom: nil)
        let child = WidgetEntityContainer(type: "beagle:Text", content: content)
        guard let sut = try? PaddingEntity(value: value, childContainer: child) else {
            XCTFail("Could not create PaddingEntity.")
            return
        }

        // When
        let padding = try? sut.mapToWidget()

        // Then
        XCTAssertNotNil(padding, "The Padding widget should not be nil.")
        XCTAssertTrue(padding is Padding)
    }

    func test_whenMapToWidgetIsCalledWithInvalidChildContent_thenItShouldThrowAChildError() {
        // Given
        let value = PaddingValueEntity(top: nil, left: nil, right: nil, bottom: nil)
        let child = WidgetEntityContainer(type: "beagle:Text", content: nil)
        guard let sut = try? PaddingEntity(value: value, childContainer: child) else {
            XCTFail("Could not create PaddingEntity.")
            return
        }

        // When / Then
        XCTAssertThrowsError(
            try sut.mapToWidget(),
            "Expected to Throw an error, but it didn't."
        )
    }
    
}
