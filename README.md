# LoginFlow : Student Learning Dashboard

A modern Android application built with Jetpack Compose that provides a comprehensive student learning dashboard with authentication, performance tracking, and notification management.

## 🚀 Features

- **Authentication**: Secure login/signup functionality
- **Student Dashboard**: Comprehensive learning overview with stats and performance metrics
- **Weekly Progress Tracking**: Quiz streaks, accuracy monitoring, and topic-wise performance
- **Notification Management**: Customizable notification settings
- **Modern UI**: Built with Jetpack Compose and Material Design 3
- **Consistent Theming**: Centralized color and typography system

## 🛠 Tech Stack

- **Language**: Kotlin
- **UI Framework**: Jetpack Compose
- **Architecture**: MVVM with Clean Architecture
- **Dependency Injection**: Hilt
- **Networking**: Retrofit
- **Async Programming**: Coroutines & Flow
- **Build Tool**: Gradle with Kotlin DSL

## 📋 Prerequisites

- **Android Studio**: Iguana or later
- **Minimum SDK**: API 24 (Android 7.0)
- **Target SDK**: API 34 (Android 14)
- **Kotlin**: 1.9.0+

## 🔧 Quick Setup

### Manual Setup Steps

1. **Clone the Repository**
```bash
git clone <repository-url>
cd LoginFlow
```

2. **Open in Android Studio**
- Launch Android Studio
- Select "Open an existing Android Studio project"
- Navigate to the cloned directory and select it

3. **Configure Firebase (Optional)**
- Add your `google-services.json` file to `app/` directory
- The app will work without Firebase for local development

4. **Build the Project**
```bash
# Using Android Studio
Build → Make Project

# Or using command line
./gradlew assembleDebug
```

5. **Run on Device/Emulator**
- Connect an Android device or start an emulator
- Click the "Run" button in Android Studio (Shift + F10)
- Select your target device


## 📊 Data Flow

```
User Interaction → ViewModel → Use Case → Repository → API
                      ↓           ↓         ↓         ↓
                State Update ← Response ← Data ← Network
```



## 📂 Project Structure

```
app/src/main/java/com/example/loginflow/
├── common/              # 🔧 Shared utilities and resources
├── data/                # 💾 Data layer implementation
│   ├── StudentInfoApi.kt
│   ├── StudentInfoDTO.kt
│   └── StudentInfoRepoImpl.kt
├── di/                  # 🔗 Dependency injection
│   └── AppModule.kt
├── domain/              # 🎯 Business logic
│   ├── GetStudentsInfoUseCase.kt
│   └── StudentInfoRepository.kt
├── presentation/        # 📱 UI layer
│   ├── authentication/  # 🔐 Auth screens
│   │   ├── AuthState.kt
│   │   ├── AuthViewModel.kt
│   │   ├── LoginPage.kt
│   │   └── SignUpPage.kt
│   ├── home/           # 🏠 Dashboard
│   │   ├── components/
│   │   │   ├── dashboard/
│   │   │   │   ├── DashboardHeader.kt
│   │   │   │   ├── StatsCardRow.kt
│   │   │   │   └── StudentDashboardContent.kt
│   │   │   ├── summary/
│   │   │   │   └── SummaryCard.kt
│   │   │   └── weeklyoverview/
│   │   │       ├── AccuracyCard.kt
│   │   │       ├── PerformanceByTopicCard.kt
│   │   │       └── QuizStreakCard.kt
│   │   ├── StudentInfoListState.kt
│   │   ├── StudentsListScreen.kt
│   │   └── StudentsListViewModel.kt
│   └── notification/   # 🔔 Settings
│       ├── components/
│       │   ├── NotificationItem.kt
│       │   └── SettingsItem.kt
│       ├── NotificationsSettingsScreen.kt
│       ├── AuthNavigationEffect.kt
│       └── Routes.kt
├── ui/theme/           # 🎨 Design system
│   ├── Color.kt        # Color palette
│   ├── Theme.kt        # Material theme
│   └── Type.kt         # Typography
└── MainActivity.kt
```

## 🎨 Design System

### Typography
- **Consistent even-numbered font sizes**: 8sp, 10sp, 12sp, 14sp, 16sp, 18sp, 20sp, 22sp, 24sp, 28sp, 32sp
- **Centralized in**: `ui/theme/Type.kt`
- **Benefits**: Maintains visual hierarchy and consistency across all screens.

### Colors
- **Organized color palette** in `ui/theme/Color.kt`
- **Semantic naming** for better maintainability.

## 📋 Development Approach & Timeline

This assignment was developed following a structured, feature-driven approach:
                           

### Detailed Implementation Steps

#### 1. 🎨 Design Notification Screen
**Objective**: Create a comprehensive notification management interface
**Components Created**:
- `NotificationsSettingsScreen.kt` - Main settings screen
- `NotificationItem.kt` - Individual notification cards with color-coded categories
- `SettingsItem.kt` - Reusable settings components



#### 2. 🔗 Integrate API to Home Screen
**Objective**: Connect backend services and display student data
**Integration Points**:
- `StudentInfoApi.kt` - Retrofit service interface for REST API calls
- `StudentInfoRepoImpl.kt` - Repository implementation
- `GetStudentsInfoUseCase.kt` - Business logic encapsulation

**Data Flow**: API → Repository → Use Case → ViewModel → UI

#### 3. 🏠 Create UI for Home Screen
**Objective**: Build comprehensive dashboard experience
**Screens Created**:
- `StudentsListScreen.kt` - Main dashboard container with state management
- Dashboard components:
  - `DashboardHeader.kt` - Student info and notifications access
  - `StatsCardRow.kt` - Performance metrics cards (Availability, Quiz attempts, Accuracy)
  - `WeeklyOverviewSection.kt` - Weekly progress summary

**UI Components**:
- Stats cards with color-coded borders and backgrounds
- Progress indicators and charts
- Weekly overview with streak tracking

#### 4. 🔐 Implement Authentication
**Objective**: Secure user access and session management
**Components**:
- `AuthViewModel.kt` - Authentication business logic and state management
- `LoginPage.kt` - Login interface with form validation
- `SignUpPage.kt` - Registration interface
- `AuthNavigationEffect.kt` - Navigation state management
- `AuthState.kt` - Authentication state definitions

**Features**:
- Email/password authentication
- Form validation and error handling
- Session persistence
- Automatic navigation based on auth state

#### 5. 🌍 Internationalization (strings.xml)
**Objective**: Move hardcoded strings to resource files for better maintainability
**Benefits**:
- Multi-language support ready
- Centralized string management
- Easier maintenance and updates
- Better accessibility


#### 6. 🎨 Consistent Font Styling
**Objective**: Establish design system consistency across the entire application
**Solution**:
- Centralized typography in `ui/theme/Type.kt`
- Even-numbered font sizes only: 8sp, 10sp, 12sp, 14sp, 16sp, 18sp, 20sp, 22sp, 24sp, 28sp, 32sp
- Applied across all 14+ UI components
- Replaced all odd-numbered fonts (11sp→10sp, 13sp→12sp, 30sp→28sp)


## 🔄 Iterative Development Process

```
Plan → Implement → Test → Refactor → Document
   ↑                                            ↓
   └────────────── Continuous Improvement ──────┘
```

This approach ensured:
- **Incremental Progress**: Each step built upon the previous with clear milestones
- **Quality Assurance**: Testing and validation at each stage
- **Documentation**: Clear development trail and architecture decisions
- **Flexibility**: Easy to adapt and extend features as requirements evolved


## 🚀 Production Ready

The application is production-ready with:
- Proper error handling and loading states
- Secure authentication flow with session management
- Responsive and accessible UI following Material Design guidelines
- Clean, maintainable codebase with clear architecture
- Comprehensive documentation for future development
- Internationalization support for global markets

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request
