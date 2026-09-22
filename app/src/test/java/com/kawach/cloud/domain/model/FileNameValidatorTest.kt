package com.kawach.cloud.domain.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class FileNameValidatorTest {
    @Test fun acceptsNormalName() {
        assertTrue(FileNameValidator.validate("photo.jpg").isSuccess)
    }

    @Test fun rejectsEmptyName() {
        assertFalse(FileNameValidator.validate("   ").isSuccess)
    }

    @Test fun rejectsPathSeparators() {
        assertFalse(FileNameValidator.validate("folder/file").isSuccess)
    }

    @Test fun rejectsDuplicateIgnoringCase() {
        assertFalse(FileNameValidator.validate("PHOTO.JPG", setOf("photo.jpg")).isSuccess)
    }

    @Test fun createsFolderWithTrimmedName() {
        val folder = FileNameValidator.newFolder("  Documents  ")
        assertEquals("Documents", folder.name)
        assertEquals(null, folder.parentId)
    }
}
