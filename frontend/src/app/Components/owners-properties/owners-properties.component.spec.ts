import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OwnersPropertiesComponent } from './owners-properties.component';

describe('OwnersPropertiesComponent', () => {
  let component: OwnersPropertiesComponent;
  let fixture: ComponentFixture<OwnersPropertiesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OwnersPropertiesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(OwnersPropertiesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
