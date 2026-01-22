# Makefile for lib-ek library + tests
# Creates lib-ek.jar and runs tests from tests/

# Config
LIB_NAME = lib_ekrebsv0.jar
OUT_DIR = out
LIB_CLASSES_DIR = $(OUT_DIR)/lib-classes
TEST_CLASSES_DIR = $(OUT_DIR)/test-classes
SRC_DIR = src
TEST_DIR = tests

# Find all java sources recursively
SOURCES = $(shell find $(SRC_DIR) -name "*.java")
TEST_SOURCES = $(shell find $(TEST_DIR) -name "*.java")

# colors
ERRO = \033[1;31m
SUCC = \033[1;32m
WARN = \033[1;33m
INFO = \033[1;34m
RESET = \033[0m

#function
# Print info function
define print_info
	@echo "$(INFO)[INFO]$(RESET) $(1)"
endef

define print_success
	@echo "$(SUCC)[DONE]$(RESET) $(1)"
endef

define print_warning
	@echo "$(WARN)[WARN]$(RESET) $(1)"
endef

define print_error
	@echo "$(ERRO)[ERR] $(RESET) $(1)"
endef





##############--------- MAKEFILE ---------##############

all: lib

# Compile library sources to lib-classes/
$(LIB_CLASSES_DIR)/%.class: $(SOURCES)
	@$(call print_info,"Compiling library Java sources...")
	mkdir -p $(LIB_CLASSES_DIR)
	javac -Xlint:all -Werror -d $(LIB_CLASSES_DIR) $(SOURCES)

# Create library JAR from lib-classes/ only (strips out/ prefix)
$(LIB_NAME): $(LIB_CLASSES_DIR)/%.class
	@$(call print_info,"linking the sources into library...")
	jar cf $(LIB_NAME) -C $(LIB_CLASSES_DIR) .

# Main target: build lib
lib: $(LIB_NAME) liblist
	@$(call print_success, )

# List JAR contents
liblist:
	@$(call print_info,"Listing all JAR contents...")
	jar tf $(LIB_NAME)

# Compile and run tests (uses separate test-classes/)
test: lib
	@$(call print_info,"Running the tests...")
	mkdir -p $(TEST_CLASSES_DIR)
	javac -cp "$(TEST_DIR):$(SRC_DIR):$(LIB_NAME):$(LIB_CLASSES_DIR)" \
	      -d $(TEST_CLASSES_DIR) $(TEST_SOURCES)
	java -cp "$(TEST_CLASSES_DIR):$(LIB_NAME):$(LIB_CLASSES_DIR)"

# Clean everything
clean:
	@$(call print_info,"Cleaning build directory...")
	rm -rf $(OUT_DIR) $(LIB_NAME)

re: clean all

.PHONY: all lib test clean liblist


