//
//  Copyright © 01/11/19 Zup IT. All rights reserved.
//

import XCTest
@testable import BeagleUI

final class NetworkImageTests: XCTestCase {

    private let dependencies = BeagleScreenDependencies()
    
    func test_withInvalidURL_itShouldNotSetImage() throws {
        // Given
        let component = NetworkImage(path: "www.com")
        
        // When
        guard let imageView = component.toView(context: BeagleContextDummy(), dependencies: BeagleScreenDependencies()) as? UIImageView else {
            XCTFail("Build view not returning UIImageView")
            return
        }
        
        // Then
        XCTAssertNil(imageView.image, "Expected image to be nil.")
    }
}